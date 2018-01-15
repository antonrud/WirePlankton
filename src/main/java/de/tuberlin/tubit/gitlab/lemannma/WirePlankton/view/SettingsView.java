package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;

import java.net.InetAddress;
import java.util.ArrayList;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.SettingsController;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs.MultiChoice;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs.NumberInput;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs.SingleChoice;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class SettingsView extends HBox{

	public SettingsView(){

		super();
		for (Setting setting : MainController.getSettings()) {
			switch(setting.getFieldType()){

			case "SINGLECHOICE":
				SingleChoice f = new SingleChoice(setting);
				this.getChildren().add(f);
				break;
			case "MULTICHOICE":
				MultiChoice m = new MultiChoice(setting);
				this.getChildren().add(m);
				break;
			case "NUMBER":
				NumberInput n = new NumberInput(setting);
				this.getChildren().add(n);
				break;
			}
		}
	}
}
