package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import java.util.LinkedList;
import java.util.List;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;

public class SettingsController {

	static LinkedList<Setting> settingsList = new LinkedList<Setting>();
	static LinkedList<Setting> exportSettingsList = new LinkedList<Setting>();
	static LinkedList<Setting> displaySettingsList = new LinkedList<Setting>();
	static LinkedList<Setting> statSettingsList = new LinkedList<Setting>();

	public static LinkedList<Setting> getSettigsList() {

		return settingsList;
	}

	public static Setting getSetting(String id) {
		return settingsList.stream().filter(setting -> setting.getId().equals(id)).findFirst().get();
	}

	public static void addSetting(Setting s) {
		settingsList.add(s);
	}

	public static List<Setting> getExportSettingsList() {
		return exportSettingsList;
	}

	public static void addExportSetting(Setting s) {
		exportSettingsList.add(s);
	}

	public static LinkedList<Setting> getDisplaySettingsList() {
		return displaySettingsList;
	}

	public static LinkedList<Setting> getStatSettingsList() {
		return statSettingsList;
	}

	public static void addStatSetting(Setting s) {
		statSettingsList.add(s);

	}

	public static void addDisplaySetting(Setting s) {
		displaySettingsList.add(s);

	}

}
