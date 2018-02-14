
/*
 * Copyright (c) 2017-2018 Anton Rudacov, Stefan Pawlowski, Matthias Lehmann, Svetlana Lepikhine
 *
 * WirePlankton
 * A small network traffic analyzer.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.roche.rudacova.test;

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

/**
 * This class is essential part of WirePlankton. It provides triggers from GUI
 * to other controllers and supplies GUI with data.
 *
 * @author Anton Rudacov
 * @author Stefan Pawlowski
 * @author Matthias Lehmann
 * @author Svetlana Lepikhine
 * 
 */
public class MainController {

	/** The list of packets. */
	private static ObservableList<PacketItem> packetList = FXCollections.observableArrayList();

	/** The list of available interfaces. */
	private static Map<String, String> interfaces;

	/** The thread for capturing packets. */
	static Thread captureThread = new Thread();

	/** The Pcap handle. */
	static PcapHandle handle;

	/**
	 * The MainController only have static methods.
	 */
	private MainController() {

	}

	/**
	 * This method instantiates CaptureController as new thread and starts it.
	 *
	 * @see CaptureController
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
	 * Closes Pcap handle.
	 */
	public static void closeHandle() {
		if (handle != null)
			handle.close();
	}

	/**
	 * Interrupts capturing thread.
	 */
	public static void stopCapture() {

		captureThread.interrupt();
	}

	/**
	 * Exports packet information as CSV.
	 * 
	 * @see ImportExportController#doCSVExport(String path)
	 *
	 * @param f
	 *            the file for export
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
	 * Exports statistics information as CSV.
	 * 
	 * @see StatisticController#doExportAsText(String path)
	 *
	 * @param f
	 *            the file for export statistics
	 */
	public static void doStatisticsExport(File f) {
		try {
			StatisticController.doExportAsText(f.getPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Saves packets as Pcap dump.
	 *
	 * @see ImportExportController#doSave(ObservableList<PacketItem> packetList)
	 *
	 * @param f
	 *            the file for saving dumped packets
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
	 * Loads packets from Pcap dump.
	 *
	 * @see ImportExportController#doLoad()
	 *
	 * @param f
	 *            the file for loading dumped packets
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
	 * Gets the filter as string.
	 *
	 * @param purpose
	 *            the filtering purpose (capture, safe, load)
	 * @return filter as string in Bpf format
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
	 * Adds the packet to packetList and sends packet for evaluation.
	 *
	 * @see StatisticController#evaluatePacket(Packet packet)
	 *
	 * @param packet
	 *            the captured packet
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
	 * Clears the packet list.
	 */
	public static void clearPacketList() {
		packetList.clear();
	}

	/**
	 * Adds the setting.
	 *
	 * @param s
	 *            the setting
	 */
	public static void addSetting(Setting s) {
		SettingsController.addSetting(s);

	}

	/**
	 * Gets the setting.
	 *
	 * @param id
	 *            the id of the setting
	 * @return the setting
	 */
	public static Setting getSetting(String id) {
		return SettingsController.getSetting(id);
	}

	/**
	 * Gets the export setting.
	 *
	 * @param id
	 *            the id of the export setting
	 * @return the export setting
	 */
	public static Setting getExportSetting(String id) {
		return SettingsController.getExportSetting(id);
	}

	/**
	 * Adds the export setting.
	 *
	 * @param s
	 *            the export setting
	 */
	public static void addExportSetting(Setting s) {
		SettingsController.addExportSetting(s);

	}

	/**
	 * Adds the statistic setting.
	 *
	 * @param s
	 *            the statistic setting
	 */
	public static void addStatSetting(Setting s) {
		SettingsController.addStatSetting(s);

	}

	/**
	 * Adds the display setting.
	 *
	 * @param s
	 *            the display setting
	 */
	public static void addDisplaySetting(Setting s) {
		SettingsController.addDisplaySetting(s);

	}

	/**
	 * Gets the export settings list.
	 *
	 * @return the export settings as list
	 */
	public static List<Setting> getExportSettings() {
		return SettingsController.getExportSettingsList();
	}

	/**
	 * Gets the settings list.
	 *
	 * @return the settings as list
	 */
	public static LinkedList<Setting> getSettings() {

		return SettingsController.getSettingsList();
	}

	/**
	 * Gets the display settings list.
	 *
	 * @return the display settings as list
	 */
	public static LinkedList<Setting> getDisplaySettingsList() {
		return SettingsController.getDisplaySettingsList();
	}

	/**
	 * Gets the statistic settings list.
	 *
	 * @return the statistic settings as list
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
	 *            the interfaces map with name and description
	 */
	public static void setInterfaces(Map<String, String> map) {
		interfaces = map;
	}

	/**
	 * Gets the name of interface by description.
	 *
	 * @param interfaces
	 *            the interfaces map
	 * @param description
	 *            the description of interface
	 * @return the name of interface
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
	 * Gets the top IPv4 by captured amount.
	 *
	 * @see StatisticController#getTopIP4()
	 *
	 * @return the top IPv4 by amount
	 */
	public static Map<String, Integer> getTopIP4() {

		return StatisticController.getTopIP4();
	}

	/**
	 * Gets the top IPv6 by captured amount.
	 *
	 * @see StatisticController#getTopIP6()
	 *
	 * @return the top IPv6 by amount
	 */
	public static Map<String, Integer> getTopIP6() {

		return StatisticController.getTopIP6();
	}

	/**
	 * Gets the top MACs by captured amount.
	 *
	 * @see StatisticController#getTopMAC()
	 *
	 * @return the top MACs by amount
	 */
	public static Map<String, Integer> getTopMAC() {

		return StatisticController.getTopMAC();
	}

	/**
	 * Gets the percentage of captured packets by IP version.
	 *
	 * @see StatisticController#getPercentageIP(String version)
	 *
	 * @param version
	 *            the version of IP protocol (ip4, ip6)
	 * @return the percentage of packets
	 */
	public static float getPercentageIP(String version) {

		return StatisticController.getPercentageIP(version);
	}

	/**
	 * Gets the percentage of captured packets by packet type.
	 *
	 * @see StatisticController#getPercentageType(String type)
	 *
	 * @param version
	 *            the type of packet (tcp, udp)
	 * @return the percentage of packets
	 */
	public static float getPercentageType(String type) {

		return StatisticController.getPercentageType(type);
	}

	/**
	 * Reset statistics.
	 * 
	 * @see StatisticController#reset()
	 */
	public static void resetStatistic() {
		StatisticController.reset();
	}
}
