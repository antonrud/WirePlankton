package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model;

import java.util.ArrayList;

/**
 * Supports Integer, Choice, Multichoice,
 * @author Stef
 *
 */
public class Setting {

	int id;
	String name;
	ArrayList <String> values;
	String fieldType;

	public Setting(String name, ArrayList<String> values, String fieldType){
		this.name = name;
		this.values = values;
		this.fieldType = fieldType;
	}

	//don't get that, is it a setter or should it be really a constructor?
	public Setting(int id) {

		this.id = id;
	}

	public int getId() {

		return this.id;
	}

	public void setId(int id) {

		this.id = id;
	}
}
