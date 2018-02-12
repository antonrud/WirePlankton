/*
 * Copyright (c) 2017-2018 Anton Rudacov, Stefan Pawlowski, Matthias Lehmann, Svetlana Lepikhine
 *
 * WirePlankton
 * A small network traffic analyzer.
 * 
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
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
