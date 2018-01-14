package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs;

import java.util.LinkedList;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener.MultiChoiceEventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MultiChoice extends VBox {

	Setting setting;
	Label l;

	public MultiChoice(Setting setting){


		for(String s : setting.getChoices()){
			CheckBox b = new CheckBox(s);
			b.setSelected(true);
			b.setOnAction(new MultiChoiceEventHandler<ActionEvent>(this.setting));
			this.getChildren().add(b);
		}
	}

}
