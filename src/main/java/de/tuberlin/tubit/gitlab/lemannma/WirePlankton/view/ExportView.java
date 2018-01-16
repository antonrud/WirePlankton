package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs.MultiChoice;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs.NumberInput;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs.SingleChoice;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ExportView extends VBox{

	public ExportView(){

		super();
		for (Setting setting : MainController.getExportSettings()) {
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
