package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs;

import java.io.File;

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
		this.l = new Label(setting.getName());


		this.getChildren().add(l);
		numbers = new NumberField(setting);
		this.getChildren().add(numbers);

		//Style
		File css = new File("styles/settings.css");
		this.getStyleClass().add("numbers");
		this.l.getStyleClass().add("settingsHeader");
		this.l.getStylesheets().add("file:///"+css.getAbsolutePath().replace("\\", "/"));
		this.getStylesheets().add("file:///"+css.getAbsolutePath().replace("\\", "/"));

	}


}