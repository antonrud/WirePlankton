package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import java.net.InetAddress;
import java.nio.file.Path;
import java.util.ArrayList;
import org.pcap4j.packet.Packet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.PacketViewItem;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;

/**
 * @author Anton Rudacov
 *
 */
public class MainController {

	// Changed type from ArrayList<Packet> to ObservableList<PacketViewItem>
	private static ObservableList<PacketViewItem> packetList = FXCollections.observableArrayList();
	static Thread captureThread;

	// Shows: We want it static
	private MainController() {

	}

	public static void capturePacket(int amount, int timeout, InetAddress address) throws InterruptedException {

		captureThread = new Thread(new CaptureController(amount, timeout, address));
		captureThread.start();
		// TODO Maybe join() ??

		// Old version without threading
		// CaptureController.doCapture(amount, timeout, address);

		// This is just for checking. Must be removed after connection to GUI
		captureThread.join();
		packetList.stream().forEach(x -> System.out.println(x.getPreview()));
	}

	public static void stopCapture() {

		captureThread.interrupt();
	}

	// public static void importData(Path path) {
	// packetList = ImportExportController.doImport();
	// }

	public static void exportData(Path path) {

	}

	public static void safe(Path path) {
		// TODO failure handling
	}

	public static void load() {

	}

	public static ArrayList<Setting> getSettings() {

		return SettingsController.getSettigsList();
	}

	public static Setting getSetting(String name) {

		return SettingsController.getSettig();
	}

	public static void setSetting(Setting setting) {

	}

	public static void initApp() {

	}

	// Use PacketViewItem here!
	public static void addPacket(Packet packet, int packetNr) {

		packetList.add(new PacketViewItem(packet, packetNr));
	}

	public static ObservableList<PacketViewItem> getPacketList() {

		return packetList;
	}

	// public static void setAddress(String ip) throws Exception {
	// address = InetAddress.getByName(ip);
	// }
	//
	// public static void setLocalHost() throws Exception {
	// address = InetAddress.getLocalHost();
	// }
	//
	// public static InetAddress getAddress() {
	// return address;
	// }
}
