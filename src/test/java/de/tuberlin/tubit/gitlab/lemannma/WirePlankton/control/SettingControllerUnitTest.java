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

import static org.junit.Assert.*;

import org.junit.Test;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;

public class SettingControllerUnitTest {
	public static final int SINGLECHOICE = 0;
	
	@Test
	public void getAddDisplaySettingTest() {

		String[] dipChoice = { "all", "ipv4", "ipv6" };
		String[] dipActive = { "all" };
		String dipName = "Packettypes";
		Setting dipSetting = new Setting("D_IPVERSION", dipName, dipActive, SINGLECHOICE, dipChoice);
		SettingsController.addDisplaySetting(dipSetting);
		assertTrue(SettingsController.getDisplaySettingsList().contains(dipSetting));
	}
	
	@Test
	public void getAddStatSettingTest() {

		String[] statChoice = { "Select a Stat", "Top 5 IPv4", "Top 5 IPv6", "Top 5 MACs", "IPv4/IPv6", "TCP/UDP" };
		String[] statActive = { statChoice[0] };
		String statName = "Statistic:";
		Setting statSetting = new Setting("STAT", statName, statActive, SINGLECHOICE, statChoice);
		SettingsController.addStatSetting(statSetting);
		assertTrue(SettingsController.getStatSettingsList().contains(statSetting));
	}

}
