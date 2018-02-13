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

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener.SingleChoiceChangeSettingsListener;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs.MultiChoice;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs.NumberInput;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs.SingleChoice;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * The Class ExportView.
 *
 * @author Stefan
 */


public class ExportView extends ScrollPane{

	private VBox settingsContent;

	public ExportView(){

	/** The content of the settings and its possible cases. */
		super();
		this.settingsContent = new VBox();
		for (Setting setting : MainController.getExportSettings()) {
			switch(setting.getFieldType()){

			case 0:
				SingleChoice f = new SingleChoice(setting, new SingleChoiceChangeSettingsListener<Number>(setting));
				this.settingsContent.getChildren().add(f);
				break;
			case 1:
				MultiChoice m = new MultiChoice(setting);
				this.settingsContent.getChildren().add(m);
				break;
			case 2:
				NumberInput n = new NumberInput(setting);
				this.settingsContent.getChildren().add(n);
				break;
			}
		}
		this.setContent(this.settingsContent);

		/** Style. */
		this.setStyle("-fx-background: white");
		this.settingsContent.setStyle("-fx-background-color: white");
		//File css = new File("styles/settings.css");
		//this.settingsContent.getStyleClass().add("settingsBack");
		//this.settingsContent.getStylesheets().add("file:///"+css.getAbsolutePath().replace("\\", "/"));
	}
}
