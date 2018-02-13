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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;

/**
 * This is the main class of WirePlankton.
 */
public class WirePlankton {

	/** A setting type - SINGLECHOICE. */
	public static final int SINGLECHOICE = 0;

	/** A setting type - MULTICHOICE. */
	public static final int MULTICHOICE = 1;

	/** A setting type - NUMBER. */
	public static final int NUMBER = 2;

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		configure();

		ViewController.go();
	}

	private static void configure(){
		List<PcapNetworkInterface> interfaces = new ArrayList<PcapNetworkInterface>();
		try {
			interfaces = Pcaps.findAllDevs();
		} catch (PcapNativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Map<String, String> nifs = new HashMap<String, String>();
		for (PcapNetworkInterface nif : interfaces) {
			if (nif.getDescription() == null) {
				nifs.put(nif.getName(), nif.getName());
			} else {
				nifs.put(nif.getName(), nif.getName());
			}
		}
		MainController.setInterfaces(nifs);

		// SystemSettings
		String[] nifChoice = MainController.getInterfaceDescriptions().toArray(new String[0]);
		String[] nifActive = nifChoice;
		String nifName = "Interface:";
		Setting nifSetting = new Setting("NIF", nifName, nifActive, SINGLECHOICE, nifChoice);
		MainController.addSetting(nifSetting);

		String[] amountActive = { "17" };
		String amountName = "Packets to be captured:";
		Setting amountSetting = new Setting("AMOUNT", amountName, amountActive, NUMBER);
		MainController.addSetting(amountSetting);

		String[] limitActive = { "1000" };
		String limitName = "Max. Size in KBytes:";
		Setting limitSetting = new Setting("LIMIT", limitName, limitActive, NUMBER);
		MainController.addSetting(limitSetting);

		String[] timeoutActive = { "60" };
		String timeoutName = "Timeout in sec:";
		Setting timeoutSetting = new Setting("TIMEOUT", timeoutName, timeoutActive, NUMBER);
		MainController.addSetting(timeoutSetting);

		String[] ipChoice = { "ip", "ip6" };
		String[] ipActive = {};
		String ipName = "IP version:";
		Setting ipSetting = new Setting("IPVERSION", ipName, ipActive, MULTICHOICE, ipChoice);
		MainController.addSetting(ipSetting);

		// ExportSettings
		String[] eipChoice = { "ip", "ip6" };
		String[] eipActive = {};
		String eipName = "IP version:";
		Setting eipSetting = new Setting("E_IPVERSION", eipName, eipActive, MULTICHOICE, eipChoice);
		MainController.addExportSetting(eipSetting);

		String[] eamountActive = { "17" };
		String eamountName = "Amount of packets:";
		Setting eamountSetting = new Setting("E_AMOUNT", eamountName, eamountActive, NUMBER);
		MainController.addExportSetting(eamountSetting);

		// DisplaySettings
		String[] dipChoice = { "all", "ipv4", "ipv6" };
		String[] dipActive = { "all" };
		String dipName = "Packettypes";
		Setting dipSetting = new Setting("D_IPVERSION", dipName, dipActive, SINGLECHOICE, dipChoice);
		MainController.addDisplaySetting(dipSetting);

		// StatSettings
		String[] statChoice = { "Select a Stat", "Top 5 IPv4", "Top 5 IPv6", "Top 5 MACs", "IPv4/IPv6", "TCP/UDP" };
		String[] statActive = { statChoice[0] };
		String statName = "Statistic:";
		Setting statSetting = new Setting("STAT", statName, statActive, SINGLECHOICE, statChoice);
		MainController.addStatSetting(statSetting);
	}
}