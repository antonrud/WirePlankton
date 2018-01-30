package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;

import java.io.File;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SettingsView extends ScrollPane{

	private VBox settingsContent;

	public SettingsView(){

		super();
		this.settingsContent = new VBox();
		for (Setting setting : MainController.getSettings()) {
			switch(setting.getFieldType()){

			case "SINGLECHOICE":
				SingleChoice f = new SingleChoice(setting);
				this.settingsContent.getChildren().add(f);
				break;
			case "MULTICHOICE":
				MultiChoice m = new MultiChoice(setting);
				this.settingsContent.getChildren().add(m);
				break;
			case "NUMBER":
				NumberInput n = new NumberInput(setting);
				this.settingsContent.getChildren().add(n);
				break;
			}
		}
		this.setContent(this.settingsContent);

		//Style
		File css = new File("styles/settings.css");
		this.getStyleClass().add("settingsBack");
		this.getStylesheets().add("file:///"+css.getAbsolutePath().replace("\\", "/"));
		this.settingsContent.getStyleClass().add("settingsBack");
		this.settingsContent.getStylesheets().add("file:///"+css.getAbsolutePath().replace("\\", "/"));

		this.setStyle("-fx-background: white");
		this.settingsContent.setStyle("-fx-background-color: white");
		//File css = new File("styles/settings.css");
		//this.settingsContent.getStyleClass().add("settingsBack");
		//this.settingsContent.getStylesheets().add("file:///"+css.getAbsolutePath().replace("\\", "/"));
	}
}
