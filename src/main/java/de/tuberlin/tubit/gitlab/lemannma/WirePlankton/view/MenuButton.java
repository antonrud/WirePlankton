/*
 * Copyright (c) 2017-2018 Anton Rudacov, Stefan Pawlowski, Matthias Lehmann, Svetlana Lepikhine
 *
 * WirePlankton
 * A small network traffic analyzer.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;

import java.io.File;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.ViewController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 *The class MenuButton.
 *
 * @author Stefan
 *
 */
public class MenuButton extends Button {

	public MenuButton(String label, EventHandler<ActionEvent> event){

		super();
		/** The label. */

		this.setText(label);

		/** The integer for viewchange. */
		this.setOnAction(event);

		File css = new File("styles/menu.css");
		this.getStyleClass().add("button");
		this.getStylesheets().add("file:///"+css.getAbsolutePath().replace("\\", "/"));
	}

}
