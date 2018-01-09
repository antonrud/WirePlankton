package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;

import java.util.ArrayList;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SingleChoice extends VBox{

	ObservableList<String> inputs;

	Setting s;
	ChoiceBox field;

	public SingleChoice(String ddefault, ArrayList<String> choices){

		inputs.addAll(choices);
		field = new ChoiceBox<String>(inputs);


		this.getChildren().add(field);



	}

}
