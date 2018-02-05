package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;

import java.util.LinkedList;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs.MultiChoice;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs.NumberInput;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs.SingleChoice;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SettingsBar extends HBox{

	public SettingsBar(LinkedList<Setting> settings){
		super();
		for (Setting setting : settings) {
			switch(setting.getFieldType()){

			case 0:
				SingleChoice f = new SingleChoice(setting);
				this.getChildren().add(f);
				break;
			case 1:
				MultiChoice m = new MultiChoice(setting);
				this.getChildren().add(m);
				break;
			case 2:
				NumberInput n = new NumberInput(setting);
				this.getChildren().add(n);
				break;
			}
		}
	}

}
