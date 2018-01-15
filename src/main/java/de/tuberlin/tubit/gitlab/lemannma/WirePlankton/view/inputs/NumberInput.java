package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NumberInput extends VBox{

	Label l;
	Setting setting;
	NumberField numbers;

	public NumberInput(Setting setting){

		this.setting = setting;
		Label l = new Label(setting.getName());

		this.getChildren().add(l);
		numbers = new NumberField(setting);
		this.getChildren().add(numbers);

	}


}
