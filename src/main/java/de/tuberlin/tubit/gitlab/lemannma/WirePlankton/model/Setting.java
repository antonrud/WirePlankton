package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model;

public class Setting {
	
	int id;
	String name;
	String value;
	String fieldType;
	
	
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
