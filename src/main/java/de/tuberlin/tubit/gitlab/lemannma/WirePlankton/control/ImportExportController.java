/*
 * Copyright (c) 2017-2018 Anton Rudacov, Stefan Pawlowski, Matthias Lehmann, Svetlana Lepikhine
 *
 * WirePlankton
 * A small network traffic analyzer.
 * 
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.namednumber.DataLinkType;
import org.pcap4j.core.BpfProgram.BpfCompileMode;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapDumper;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.PacketItem;
import javafx.collections.ObservableList;

/**
 * This class is used to safe and load dumped packets. It can also export
 * packets information as CSV.
 * 
 * @author Anton Rudacov
 */
public class ImportExportController {

	/** The Constant SNAP_LEN for most possible packet size. */
	private static final int SNAP_LEN = 65536;

	/** The safe/load/export path. */
	String path;

	/** The amount of packets. */
	int amount;

	/** The filter for packets. */
	String filter;

	/**
	 * Instantiates a new ImportExportController.
	 *
	 */
	public ImportExportController(String path, int amount, String filter) {
		this.path = path;
		this.amount = amount;
		this.filter = filter;
	}

	/**
	 * Instantiates a new import export controller.
	 *
	 */
	public ImportExportController(String path) {
		this.path = path;
		this.amount = Integer.MAX_VALUE;
		this.filter = "";
	}

	/**
	 * This method loads packets from file
	 *
	 * @throws PcapNativeException
	 *             if something is wrong with Pcap
	 * @throws EOFException
	 *             if end of file is reached
	 * @throws TimeoutException
	 *             if handle is closed by timeout
	 * @throws NotOpenException
	 *             if handle is not open any more
	 */
	public void doLoad() throws PcapNativeException, EOFException, TimeoutException, NotOpenException {
		MainController.clearPacketList();
		MainController.resetStatistic();
		PacketItem.resetIndexGen();

		PcapHandle handle = Pcaps.openOffline(path);

		if (filter != null && filter.length() != 0) {
			handle.setFilter(filter, BpfCompileMode.OPTIMIZE);
		}

		Packet packet;

		if (amount <= 0) {
			while ((packet = handle.getNextPacketEx()) != null) {
				MainController.addPacket(packet);
			}
		} else {
			int count = 0;
			while ((packet = handle.getNextPacketEx()) != null && count < amount) {
				MainController.addPacket(packet);
				count++;
			}
		}

		handle.close();
	}

	/**
	 * This method saves packets to file
	 *
	 * @param packetList
	 *            list of packets to be saved
	 * @throws PcapNativeException
	 *             if something is wrong with Pcap
	 * @throws NotOpenException
	 *             if handle is not open any more
	 */
	public void doSave(ObservableList<PacketItem> packetList) throws PcapNativeException, NotOpenException {
		PcapHandle handle = Pcaps.openDead(DataLinkType.EN10MB, SNAP_LEN);

		if (filter != null && filter.length() != 0) {
			handle.setFilter(filter, BpfCompileMode.OPTIMIZE);
		}

		PcapDumper dumper = handle.dumpOpen(path);

		if (amount <= 0) {
			packetList.stream().forEach(item -> {
				try {
					dumper.dump(item.getP());
				} catch (NotOpenException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		} else {
			packetList.stream().limit(amount).forEach(item -> {
				try {
					dumper.dump(item.getP());
				} catch (NotOpenException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}

		dumper.close();
		handle.close();
	}

	/**
	 * This method exports packet information as CSV.
	 *
	 * @param packetList
	 *            list of packets to be exported
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void doCSVExport(ObservableList<PacketItem> packetList) throws IOException {

		FileWriter writer = new FileWriter(path, true);
		BufferedWriter buffer = new BufferedWriter(writer);

		buffer.write("Index;CapturedAt;OriginalLength;DestinationAddress;SourceAddress;PacketType");
		buffer.newLine();

		for (PacketItem packetItem : packetList) {
			buffer.write(packetItem.toCSVFormat());
			buffer.newLine();
		}

		buffer.close();
		writer.close();
	}
}
