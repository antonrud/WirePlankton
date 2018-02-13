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

import javax.naming.InitialContext;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.core.Pcaps;
import org.pcap4j.core.BpfProgram.BpfCompileMode;
import org.pcap4j.packet.Packet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.PacketItem;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;

// TODO: Auto-generated Javadoc
/**
 * The Class MainController.
 *
 * @author Anton, Stefan, Matthias, Lana
 */
public class MainController {

	/** The packet list. */
	private static ObservableList<PacketItem> packetList = FXCollections.observableArrayList();

	/** The interfaces. */
	private static Map<String, String> interfaces;

	/** The capture thread. */
	static Thread captureThread = new Thread();

	/** The handle. */
	static PcapHandle handle;

	/**
	 * Instantiates a new main controller.
	 */
	// Shows: We want it static
	private MainController() {

	}

	/**
	 * Capture packet.
	 *
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static void capturePacket() throws InterruptedException {

		// TODO Settings IDs must be defined.
		int amount = Integer.parseInt(getSetting("AMOUNT").getActive().get(0));
		int limit = (Integer.parseInt(getSetting("LIMIT").getActive().get(0))) * 1024;
		int timeout = (Integer.parseInt(getSetting("TIMEOUT").getActive().get(0))) * 1000;
		String filter = getFilterString("capture");

		try {
			handle = Pcaps
					.getDevByName(getNameByDescription(interfaces, getSetting("NIF").getActive().get(0)).toString())
					.openLive(65536, PromiscuousMode.PROMISCUOUS, timeout);
		} catch (PcapNativeException e1) {
			System.out.println("[FAIL] Somthing went wrong with pcap. Sorry for that.");
			e1.printStackTrace();
		}

		if (filter != null && filter.length() != 0) {
			try {
				handle.setFilter(filter, BpfCompileMode.OPTIMIZE);
			} catch (PcapNativeException e) {
				System.out.println("[FAIL] Somthing went wrong with capture filter. Sorry for that.");
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotOpenException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		captureThread = new Thread(new CaptureController(handle, amount, limit));
		captureThread.start();
	}

	/**
	 * Close handle.
	 */
	public static void closeHandle() {
		if (handle != null)
			handle.close();
	}

	/**
	 * Stop capture.
	 */
	public static void stopCapture() {

		captureThread.interrupt();
	}

	/**
	 * Do CSV export.
	 *
	 * @param f
	 *            the f
	 */
	public static void doCSVExport(File f) {
		ImportExportController exportController = new ImportExportController(f.getPath());

		try {
			exportController.doCSVExport(packetList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Do save.
	 *
	 * @param f
	 *            the f
	 */
	public static void doSave(File f) {

		int amount = Integer.parseInt(getExportSetting("E_AMOUNT").getActive().get(0));
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

	/**
	 * Do load.
	 *
	 * @param f
	 *            the f
	 */
	public static void doLoad(File f) {

		int amount = Integer.parseInt(getExportSetting("E_AMOUNT").getActive().get(0));
		String filter = getFilterString("load");

		ImportExportController loadController = new ImportExportController(f.getPath(), amount, filter);

		try {
			loadController.doLoad();
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

	/**
	 * Gets the filter string.
	 *
	 * @param purpose
	 *            the purpose
	 * @return the filter string
	 */
	private static String getFilterString(String purpose) {
		String filter = "";
		List<String> filterIds;

		switch (purpose) {
		case "capture":
			filterIds = Arrays.asList("IPVERSION");

			for (Setting filterSetting : getSettings().stream().filter(setting -> filterIds.contains(setting.getId()))
					.filter(setting -> !setting.getActive().isEmpty()).collect(Collectors.toList())) {
				for (String active : filterSetting.getActive()) {
					filter = filter + active + " or ";
				}
			}

			break;

		case "save":
		case "load":
			filterIds = Arrays.asList("E_IPVERSION");

			for (Setting filterSetting : getExportSettings().stream()
					.filter(setting -> filterIds.contains(setting.getId()))
					.filter(setting -> !setting.getActive().isEmpty()).collect(Collectors.toList())) {
				for (String active : filterSetting.getActive()) {
					filter = filter + active + " or ";
				}
			}

			break;

		default:
			throw new IllegalArgumentException("Invalid purpose: " + purpose);
		}

		if (filter.length() != 0) {
			filter = filter.substring(0, filter.length() - 4); // Cut off the last " or "
		}

		return filter;
	}

	/**
	 * Inits the context.
	 */
	public static void initContext() {
		stopCapture();
		clearPacketList();
		PacketItem.resetIndexGen();
		SettingsController.settingsList.clear();
		StatisticController.reset();
	}
	
	/**
	 * Inits the app.
	 */
	public static void initApp() {
		initContext();
		WirePlankton.main(new String[0]);
	}

	/**
	 * Adds the packet.
	 *
	 * @param packet
	 *            the packet
	 */
	public static void addPacket(Packet packet) {
		packetList.add(new PacketItem(packet));
		StatisticController.evaluatePacket(packet);
	}

	/**
	 * Gets the packet list.
	 *
	 * @return the packet list
	 */
	public static ObservableList<PacketItem> getPacketList() {
		return packetList;
	}

	/**
	 * Clear packet list.
	 */
	public static void clearPacketList() {
		packetList.clear();
	}

	/**
	 * Adds the setting.
	 *
	 * @param s
	 *            the s
	 */
	public static void addSetting(Setting s) {
		SettingsController.addSetting(s);

	}

	/**
	 * Gets the setting.
	 *
	 * @param id
	 *            the id
	 * @return the setting
	 */
	public static Setting getSetting(String id) {
		return SettingsController.getSetting(id);
	}

	/**
	 * Gets the export setting.
	 *
	 * @param id
	 *            the id
	 * @return the export setting
	 */
	public static Setting getExportSetting(String id) {
		return SettingsController.getExportSetting(id);
	}

	/**
	 * Adds the export setting.
	 *
	 * @param s
	 *            the s
	 */
	public static void addExportSetting(Setting s) {
		SettingsController.addExportSetting(s);

	}

	/**
	 * Adds the stat setting.
	 *
	 * @param s
	 *            the s
	 */
	public static void addStatSetting(Setting s) {
		SettingsController.addStatSetting(s);

	}

	/**
	 * Adds the display setting.
	 *
	 * @param s
	 *            the s
	 */
	public static void addDisplaySetting(Setting s) {
		SettingsController.addDisplaySetting(s);

	}

	/**
	 * Gets the export settings.
	 *
	 * @return the export settings
	 */
	public static List<Setting> getExportSettings() {
		return SettingsController.getExportSettingsList();
	}

	/**
	 * Gets the settings.
	 *
	 * @return the settings
	 */
	public static LinkedList<Setting> getSettings() {

		return SettingsController.getSettigsList();
	}

	/**
	 * Gets the display settings list.
	 *
	 * @return the display settings list
	 */
	public static LinkedList<Setting> getDisplaySettingsList() {
		return SettingsController.getDisplaySettingsList();
	}

	/**
	 * Gets the stat settings list.
	 *
	 * @return the stat settings list
	 */
	public static LinkedList<Setting> getStatSettingsList() {
		return SettingsController.getStatSettingsList();
	}

	/**
	 * Gets the interface descriptions.
	 *
	 * @return the interface descriptions
	 */
	public static Collection<String> getInterfaceDescriptions() {
		return interfaces.values();
	}

	/**
	 * Sets the interfaces.
	 *
	 * @param map
	 *            the map
	 */
	public static void setInterfaces(Map<String, String> map) {
		interfaces = map;
	}

	/**
	 * Gets the name by description.
	 *
	 * @param interfaces
	 *            the interfaces
	 * @param description
	 *            the description
	 * @return the name by description
	 */
	public static Object getNameByDescription(Map<String, String> interfaces, Object description) {
		for (Object name : interfaces.keySet()) {
			if (interfaces.get(name).equals(description)) {
				return name;
			}
		}
		return null;
	}

	/**
	 * Gets the top IP 4.
	 *
	 * @return the top IP 4
	 */
	public static Map<String, Integer> getTopIP4() {

		return StatisticController.getTopIP4();
	}

	/**
	 * Gets the top IP 6.
	 *
	 * @return the top IP 6
	 */
	public static Map<String, Integer> getTopIP6() {

		return StatisticController.getTopIP6();
	}

	/**
	 * Gets the top MAC.
	 *
	 * @return the top MAC
	 */
	public static Map<String, Integer> getTopMAC() {

		return StatisticController.getTopMAC();
	}

	/**
	 * Gets the percentage IP.
	 *
	 * @param version
	 *            the version
	 * @return the percentage IP
	 */
	public static float getPercentageIP(String version) {

		return StatisticController.getPercentageIP(version);
	}

	/**
	 * Gets the percentage type.
	 *
	 * @param type
	 *            the type
	 * @return the percentage type
	 */
	public static float getPercentageType(String type) {

		return StatisticController.getPercentageType(type);
	}

	/**
	 * Reset statistic.
	 */
	public static void resetStatistic() {
		StatisticController.reset();
	}
}
