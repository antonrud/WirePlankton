package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import java.io.EOFException;
import java.util.concurrent.TimeoutException;

import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.namednumber.DataLinkType;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapDumper;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.PacketViewItem;
import javafx.collections.ObservableList;

public class ImportExportController {

	String path;

	public ImportExportController(String path) {
		this.path = path;
	}

	public void doImport() throws PcapNativeException, EOFException, TimeoutException, NotOpenException {
		MainController.clearPacketList();

		PcapHandle handle = Pcaps.openOffline(path);

		Packet packet;
		int packetNr = 1;

		while ((packet = handle.getNextPacketEx()) != null) {
			MainController.addPacket(packet, packetNr);
			packetNr++;
		}

		handle.close();
	}

	public void doExport(ObservableList<PacketViewItem> packetList) throws PcapNativeException, NotOpenException {
		PcapHandle handle = Pcaps.openDead(DataLinkType.EN10MB, 65536);
		PcapDumper dumper = handle.dumpOpen(path);

		packetList.stream().forEach(item -> {
			try {
				dumper.dump(item.getP());
			} catch (NotOpenException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		// File file = new File("Dump.pcap");

		dumper.close();
		handle.close();
	}
}
