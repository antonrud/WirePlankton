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

import java.util.LinkedList;
import java.util.List;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;

/**
 * This class facilitates settings.
 * 
 * @author Stefan Pawlowski
 */
public class SettingsController {

	/** The settings list for capturing. */
	static LinkedList<Setting> settingsList = new LinkedList<Setting>();

	/** The export settings list. */
	static LinkedList<Setting> exportSettingsList = new LinkedList<Setting>();

	/** The display settings list. */
	static LinkedList<Setting> displaySettingsList = new LinkedList<Setting>();

	/** The settings list for statistics. */
	static LinkedList<Setting> statSettingsList = new LinkedList<Setting>();

	/**
	 * Gets settings list for capturing.
	 *
	 * @return the settings list for capturing
	 */
	public static LinkedList<Setting> getSettingsList() {

		return settingsList;
	}

	/**
	 * Gets the specific capruting setting.
	 *
	 * @param id
	 *            the id of a setting
	 * @return the specific setting
	 */
	public static Setting getSetting(String id) {
		return settingsList.stream().filter(setting -> setting.getId().equals(id)).findFirst().get();
	}

	/**
	 * Gets the specific export setting.
	 *
	 * @param id
	 *            the id of a setting
	 * @return the specific export setting
	 */
	public static Setting getExportSetting(String id) {
		return exportSettingsList.stream().filter(setting -> setting.getId().equals(id)).findFirst().get();
	}

	/**
	 * Adds the capturing setting.
	 *
	 * @param s
	 *            the new setting
	 */
	public static void addSetting(Setting s) {
		settingsList.add(s);
	}

	/**
	 * Gets the export settings list.
	 *
	 * @return the export settings list
	 */
	public static List<Setting> getExportSettingsList() {
		return exportSettingsList;
	}

	/**
	 * Adds the export setting.
	 *
	 * @param s
	 *            the new export setting
	 */
	public static void addExportSetting(Setting s) {
		exportSettingsList.add(s);
	}

	/**
	 * Gets the display settings list.
	 *
	 * @return the display settings list
	 */
	public static LinkedList<Setting> getDisplaySettingsList() {
		return displaySettingsList;
	}

	/**
	 * Gets the statistics settings list.
	 *
	 * @return the statistics settings list
	 */
	public static LinkedList<Setting> getStatSettingsList() {
		return statSettingsList;
	}

	/**
	 * Adds the statistic setting.
	 *
	 * @param s
	 *            the statistic setting
	 */
	public static void addStatSetting(Setting s) {
		statSettingsList.add(s);

	}

	/**
	 * Adds the display setting.
	 *
	 * @param s
	 *            the new display setting
	 */
	public static void addDisplaySetting(Setting s) {
		displaySettingsList.add(s);

	}

}
