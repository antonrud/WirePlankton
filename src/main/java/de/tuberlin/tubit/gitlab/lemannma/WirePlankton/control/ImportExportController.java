package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.namednumber.DataLinkType;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapDumper;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.PacketItem;
import javafx.collections.ObservableList;

public class ImportExportController {

	String path;

	public ImportExportController(String path) {
		this.path = path;
	}

	public void doLoad() throws PcapNativeException, EOFException, TimeoutException, NotOpenException {
		MainController.clearPacketList();

		PcapHandle handle = Pcaps.openOffline(path);

		Packet packet;

		while ((packet = handle.getNextPacketEx()) != null) {
			MainController.addPacket(packet);
		}

		handle.close();
	}

	public void doSave(ObservableList<PacketItem> packetList) throws PcapNativeException, NotOpenException {
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

		dumper.close();
		handle.close();
	}

	public void doCSVExport(ObservableList<PacketItem> packetList) throws IOException {

		FileWriter writer = new FileWriter(path, true);
		BufferedWriter buffer = new BufferedWriter(writer);

		for (PacketItem packet : packetList) {
			buffer.write(new PacketItem(packet.getP()).toCSVFormat());
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
