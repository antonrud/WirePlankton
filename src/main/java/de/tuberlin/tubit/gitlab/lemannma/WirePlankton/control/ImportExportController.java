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

public class ImportExportController {

	private static final int SNAP_LEN = 65536;

	String path;
	int amount;
	String filter;

	public ImportExportController(String path, int amount, String filter) {
		this.path = path;
		this.amount = amount;
		this.filter = filter;
	}

	public ImportExportController(String path) {
		this.path = path;
		this.amount = Integer.MAX_VALUE;
		this.filter = "";
	}

	public void doLoad() throws PcapNativeException, EOFException, TimeoutException, NotOpenException {
		MainController.clearPacketList();
		PacketItem.resetIndexGen();

		PcapHandle handle = Pcaps.openOffline(path);

		if (!filter.equals("")) {
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

	public void doSave(ObservableList<PacketItem> packetList) throws PcapNativeException, NotOpenException {
		PcapHandle handle = Pcaps.openDead(DataLinkType.EN10MB, SNAP_LEN);

		if (!filter.equals("")) {
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

	public void doCSVExport(ObservableList<PacketItem> packetList) throws IOException {

		FileWriter writer = new FileWriter(path, true);
		BufferedWriter buffer = new BufferedWriter(writer);

		for (PacketItem packetItem : packetList) {
			buffer.write(packetItem.toCSVFormat());
			buffer.newLine();
		}

		buffer.close();
		writer.close();
	}

	// TODO CSV Import can't be handled by now. Might not be necessary at all.
	// public ObservableList<PacketItem> doCSVImport() throws IOException {
	//
	// BufferedReader reader = new BufferedReader(new FileReader(path));
	// String line;
	//
	// while ((line = reader.readLine()) != null) {
	// packetList.add(new PacketItem(line));
	// }
	//
	// reader.close();
	//
	// return packetList;
	// }
}
