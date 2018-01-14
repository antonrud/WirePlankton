package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import java.util.LinkedList;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;

public class SettingsController {

	static LinkedList<Setting> settingsList = new LinkedList<Setting>();

	public static LinkedList<Setting> getSettigsList() {

		return settingsList;
	}

	public static Setting getSettig() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void addSetting(Setting s) {
		settingsList.add(s);

	}


}
