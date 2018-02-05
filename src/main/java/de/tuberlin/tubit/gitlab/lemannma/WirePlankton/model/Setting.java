package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model;

import java.util.ArrayList;

import java.util.Arrays;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.SettingsController;

/**
 * Supports Integer, Choice, Multichoice,
 *
 * @author Stef
 *
 */
public class Setting {

	private String id;
	private String name;
	private ArrayList<String> choices;
	private ArrayList<String> active;
	private int fieldType;

	public Setting(String id, String name, String[] active, int fieldType) {

		if (SettingsController.getSettigsList().stream().anyMatch(setting -> setting.getId().equals(id))) {
			try {
				throw new Exception("[FAIL] Provided setting ID is in use!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		this.id = id;
		this.name = name;
		this.active = new ArrayList<String>(Arrays.asList(active));
		this.fieldType = fieldType;
	}

	public Setting(String id, String name, String[] active, int fieldType, String[] choices) {

		if (SettingsController.getSettigsList().stream().anyMatch(setting -> setting.getId().equals(id))) {
			try {
				throw new Exception("[FAIL] Provided setting ID is in use!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		this.id = id;
		this.name = name;
		this.active = new ArrayList<String>(Arrays.asList(active));
		this.fieldType = fieldType;
		this.choices = new ArrayList<String>(Arrays.asList(choices));
	}

	public String getId() {

		return this.id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getChoices() {
		return choices;
	}

	public void setChoices(ArrayList<String> choices) {
		this.choices = choices;
	}

	public ArrayList<String> getActive() {
		return active;
	}

	public void setActive(ArrayList<String> active) {
		this.active = active;
	}

	public int getFieldType() {
		return fieldType;
	}

}
