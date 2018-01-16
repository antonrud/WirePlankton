package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import java.io.IOException;

import org.pcap4j.core.Pcaps;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapHandle.TimestampPrecision;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.PacketViewItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ImportExportController {

	String path;
	ObservableList<PacketViewItem> packetList;

	public ImportExportController(String path) {
		this.path = path;
		packetList = FXCollections.observableArrayList();
	}

	public ObservableList<PacketViewItem> doImport() throws IOException {
		PcapHandle handle = Pcaps.openOffline("file");

		for (int i = 0; i < COUNT; i++) {

			Packet packet = handle.getNextPacketEx();
			System.out.println(handle.getTimestamp());
			System.out.println(packet);

		}

		int packetNr = 1;

		while (packetNr != null) {
			MainController.addPacket(handle.getNextPacketEx(), packetNr);
			packetNr++;
		}

		handle.close();

		return packetList;
	}

	public void doExport(ObservableList<PacketViewItem> packetList) throws IOException {

		StringBuilder errbuf = new StringBuilder();
		String fname = "tests/test-afs.pcap";

		Pcap pcap = Pcap.openOffline(fname, errbuf);

		String ofile = "tmp-capture-file.cap";
		PcapDumper dumper = pcap.dumpOpen(ofile); // output file

		JBufferHandler<PcapDumper> dumpHandler = new JBufferHandler<PcapDumper>() {

			public void nextPacket(PcapHeader header, JBuffer buffer, PcapDumper dumper) {

				dumper.dump(header, buffer);
			}
		};

		pcap.loop(10, dumpHandler, dumper);

		File file = new File(ofile);
		System.out.printf("%s file has %d bytes in it!\n", ofile, file.length());

		dumper.close(); // Won't be able to delete without explicit close
		pcap.close();

	}
}
