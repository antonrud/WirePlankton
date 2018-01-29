package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs;

import java.io.File;
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
import javafx.scene.layout.VBox;


public class SingleChoice extends VBox{

	ObservableList<String> inputs;

	private Setting s;
	private ChoiceBox<String> field;
	private Label name;

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

		//style
		File css = new File("styles/settings.css");
		this.getStyleClass().add("multiChoice");
		this.name.getStyleClass().add("settingsHeader");
		this.name.getStylesheets().add("file:///"+css.getAbsolutePath().replace("\\", "/"));
		this.getStylesheets().add("file:///"+css.getAbsolutePath().replace("\\", "/"));

	}

}
