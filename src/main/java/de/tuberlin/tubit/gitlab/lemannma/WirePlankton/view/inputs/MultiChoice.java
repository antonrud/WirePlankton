package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs;

import java.io.File;
import java.util.LinkedList;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener.MultiChoiceEventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MultiChoice extends VBox {

	private Setting setting;
	private Label l;

	public MultiChoice(Setting setting){

		l = new Label(setting.getName());
		this.setting = setting;

		this.getChildren().add(l);

		for(String s : setting.getChoices()){
			CheckBox b = new CheckBox(s);
			b.setSelected(setting.getActive().contains(s));
			b.setOnAction(new MultiChoiceEventHandler<ActionEvent>(this.setting));
			this.getChildren().add(b);
		}

		//Styling
		File css = new File("styles/settings.css");
		this.getStyleClass().add("multiChoice");
		this.l.getStyleClass().add("settingsHeader");
		this.l.getStylesheets().add("file:///"+css.getAbsolutePath().replace("\\", "/"));
		this.getStylesheets().add("file:///"+css.getAbsolutePath().replace("\\", "/"));
	}

}
