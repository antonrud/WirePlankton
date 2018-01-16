package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model;

import java.util.ArrayList;

import java.util.Arrays;

/**
 * Supports Integer, Choice, Multichoice,
 * @author Stef
 *
 */
public class Setting {

	int id;
	String name;
	ArrayList <String> choices;
	ArrayList <String> active;
	String fieldType;

	public Setting(String name, String[] active, String fieldType){
		this.name = name;
		this.active = new ArrayList<String>(Arrays.asList(active));
		this.fieldType = fieldType;
	}

	public Setting(String name, String[] active, String fieldType, String[] choices){
		this.name = name;
		this.active = new ArrayList<String>(Arrays.asList(active));
		this.fieldType = fieldType;
		this.choices = new ArrayList<String>(Arrays.asList(choices));
	}

	public int getId() {

		return this.id;
	}

	public void setId(int id) {

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

	public String getFieldType() {
		return fieldType;
	}


}
