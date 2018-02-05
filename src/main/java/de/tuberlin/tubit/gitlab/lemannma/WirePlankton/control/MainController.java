package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

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
	private static Map<String, String> interfaces;
	static Thread captureThread;

	// Shows: We want it static
	private MainController() {

	}

	public static void capturePacket() throws InterruptedException {

		// TODO Settings IDs must be defined.
		int amount = Integer.parseInt(getSetting("AMOUNT").getActive().get(0));
		int limit = (Integer.parseInt(getSetting("LIMIT").getActive().get(0))) * 1024;
		int timeout = (Integer.parseInt(getSetting("TIMEOUT").getActive().get(0))) * 1000;
		String interfaceName = getNameByDescription(interfaces, getSetting("NIF").getActive().get(0)).toString();
		String filter = getFilterString("capture");

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

		int amount = Integer.parseInt(getSetting("E_AMOUNT").getActive().get(0));
		String filter = getFilterString("save");

		ImportExportController saveController = new ImportExportController(f.getPath(), amount, filter);

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

		int amount = Integer.parseInt(getSetting("E_AMOUNT").getActive().get(0));
		String filter = getFilterString("load");

		ImportExportController loadController = new ImportExportController(f.getPath(), amount, filter);

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

	private static String getFilterString(String purpose) {
		String filter = "";
		List<String> filterIds;

		switch (purpose) {
		case "capture":
			filterIds = Arrays.asList("IPVERSION");
			break;
		case "save":
		case "load":
			filterIds = Arrays.asList("E_IPVERSION");
			break;
		default:
			throw new IllegalArgumentException("Invalid purpose: " + purpose);
		}

		for (Setting filterSetting : getSettings().stream().filter(setting -> filterIds.contains(setting.getId()))
				.filter(setting -> !setting.getActive().isEmpty()).collect(Collectors.toList())) {
			for (String active : filterSetting.getActive()) {
				filter = filter + active + " or ";
			}
		}

		if (filter.length() != 0) {
			filter = filter.substring(0, filter.length() - 4); // Cut off the last " or "
		}

		return filter;
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

	public static Setting getSetting(String id) {
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

	public static LinkedList<Setting> getDisplaySettingsList() {
		return SettingsController.getDisplaySettingsList();
	}

	public static LinkedList<Setting> getStatSettingsList() {
		return SettingsController.getStatSettingsList();
	}

	public static Collection<String> getInterfaceDescriptions() {
		return interfaces.values();
	}

	public static void setInterfaces(Map<String, String> map) {
		interfaces = map;
	}

	private static Object getNameByDescription(Map<String, String> interfaces, Object description) {
		for (Object name : interfaces.keySet()) {
			if (interfaces.get(name).equals(description)) {
				return name;
			}
		}
		return null;
	}
}
