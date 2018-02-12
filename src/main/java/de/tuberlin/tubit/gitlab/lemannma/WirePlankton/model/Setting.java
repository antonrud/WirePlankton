/*
 * Copyright (c) 2017-2018 Anton Rudacov, Stefan Pawlowski, Matthias Lehmann, Svetlana Lepikhine
 *
 * WirePlankton
 * A small network traffic analyzer.
 * 
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model;

import java.util.ArrayList;

import java.util.Arrays;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.SettingsController;

/**
 * This class instantiates Setting objects.
 *
 * @author Stefan Pawlowski
 * @author Anton Rudacov
 */
public class Setting {

	/** Id of a setting. */
	private String id;

	/** Name of a setting for GUI. */
	private String name;

	/** Possible choices of a setting. */
	private ArrayList<String> choices;

	/** Active choice(s) of a setting. */
	private ArrayList<String> active;

	/** Type of a setting. */
	private int fieldType;

	/**
	 * Instantiates a new NUMBER Setting.
	 *
	 * @param id
	 *            unique id for the setting.
	 */
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

	/**
	 * Instantiates a new SINGLECHOICE or MULTICHOICE setting.
	 *
	 * @param id
	 *            unique id for the setting.
	 * @param choice
	 *            possible choice(s).
	 */
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

	/**
	 * Gets the setting id.
	 *
	 * @return the setting id
	 */
	public String getId() {

		return this.id;
	}

	/**
	 * Sets the setting id.
	 *
	 * @param id
	 *            the new setting id
	 */
	public void setId(String id) {

		this.id = id;
	}

	/**
	 * Gets the name for GUI.
	 *
	 * @return the name for GUI
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name for GUI.
	 *
	 * @param name
	 *            the new name for GUI
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the choices.
	 *
	 * @return the choices
	 */
	public ArrayList<String> getChoices() {
		return choices;
	}

	/**
	 * Sets the possible choices.
	 *
	 * @param choices
	 *            the new possible choices
	 */
	public void setChoices(ArrayList<String> choices) {
		this.choices = choices;
	}

	/**
	 * Gets the active choice(s).
	 *
	 * @return the active choice(s)
	 */
	public ArrayList<String> getActive() {
		return active;
	}

	/**
	 * Sets the active choice(s).
	 *
	 * @param active
	 *            the new active choice(s)
	 */
	public void setActive(ArrayList<String> active) {
		this.active = active;
	}

	/**
	 * Gets the type of the setting.
	 *
	 * @return the setting type
	 */
	public int getFieldType() {
		return fieldType;
	}

}
