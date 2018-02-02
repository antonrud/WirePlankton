package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.packet.Packet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.PacketItem;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;

/**
 * @author Anton, Stefan, Matthias, Lana
 *
 */
public class MainController {

	private static ObservableList<PacketItem> packetList = FXCollections.observableArrayList();
	static Thread captureThread;

	// Shows: We want it static
	private MainController() {

	}

	public static void capturePacket(int amount, int limit, int timeout, String interfaceName, String filter)
			throws InterruptedException {

		try {
			captureThread = new Thread(new CaptureController(amount, limit, timeout, interfaceName, filter));
		} catch (PcapNativeException e) {
			// TODO Auto-generated catch block
			System.out.println("[FAIL] Somthing went wrong with pcap. Sorry for that.");
			e.printStackTrace();
		}
		captureThread.start();
	}

	public static void stopCapture() {

		captureThread.interrupt();
	}

	// TODO No needed yet
	// public static void doCSVImport(File f) {
	//
	// }

	public static void doCSVExport(File f) {
		ImportExportController exportController = new ImportExportController(f.getPath());

		try {
			exportController.doCSVExport(packetList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void doSave(File f) {
		ImportExportController saveController = new ImportExportController(f.getPath());

		try {
			saveController.doSave(packetList);
		} catch (PcapNativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotOpenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void doLoad(File f) {
		ImportExportController loadController = new ImportExportController(f.getPath());

		try {
			loadController.doLoad();
		} catch (EOFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PcapNativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotOpenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void initApp() {
		stopCapture();
		packetList.clear();
		SettingsController.settingsList.clear();

		WirePlankton.main(new String[0]);
	}

	public static void addPacket(Packet packet) {
		packetList.add(new PacketItem(packet));
	}

	public static ObservableList<PacketItem> getPacketList() {
		return packetList;
	}

	public static void clearPacketList() {
		packetList.clear();
	}

	public static void addSetting(Setting s) {
		SettingsController.addSetting(s);

	}

	public static Setting getSetting(int id) {
		return SettingsController.getSetting(id);
	}

	public static void addExportSetting(Setting s) {
		SettingsController.addExportSetting(s);

	}

	public static List<Setting> getExportSettings() {
		return SettingsController.getExportSettingsList();
	}

	public static LinkedList<Setting> getSettings() {

		return SettingsController.getSettigsList();
	}
}
