package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs;

import java.util.ArrayList;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener.SingleChoiceChangeListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class SingleChoice extends HBox{

	ObservableList<String> inputs;

	Setting s;
	ChoiceBox<String> field;
	Label name;

	public SingleChoice(Setting s){

		this.s = s;
		inputs = FXCollections.observableArrayList();
		inputs.addAll(this.s.getChoices());

		field = new ChoiceBox<String>(inputs);
		field.getSelectionModel().clearAndSelect(inputs.indexOf(this.s.getActive().get(0)));
		field.getSelectionModel().selectedIndexProperty().addListener(new SingleChoiceChangeListener<Number>(this.s));
		name = new Label(s.getName());

		this.getChildren().add(name);
		this.getChildren().add(field);

	}

}
