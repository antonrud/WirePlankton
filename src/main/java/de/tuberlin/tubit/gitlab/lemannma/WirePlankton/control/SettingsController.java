package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import java.util.LinkedList;
import java.util.List;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;

public class SettingsController {

	static LinkedList<Setting> settingsList = new LinkedList<Setting>();
	static LinkedList<Setting> exportSettingsList = new LinkedList<Setting>();

	public static LinkedList<Setting> getSettigsList() {

		return settingsList;
	}

	public static Setting getSetting(int id) {
		return settingsList.stream().filter(setting -> setting.getId() == id).findFirst().get();
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

}
