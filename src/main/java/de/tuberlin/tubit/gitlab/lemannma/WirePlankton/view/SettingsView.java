package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;

import java.net.InetAddress;
import java.util.ArrayList;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.SettingsController;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs.MultiChoice;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs.SingleChoice;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class SettingsView extends HBox{

	private Button submitButton;
	private int amount = 20;
	private int timeout = 300000;

	public SettingsView(){

		super();
		this.submitButton = new Button("Start");
		this.getChildren().add(submitButton);


		submitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					InetAddress address = InetAddress.getLocalHost();
					MainController.capturePacket(amount, timeout, address);
				} catch (Exception exception) {
					// TODO Auto-generated catch block
					exception.printStackTrace();
				}
			}
		});

		buildSettings();

	}

	private void buildSettings() {

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
			case "TEXT":
				break;
			case "NUMBER":
				break;
			}
		}

	}

	public Button getSubmitButton() {

		return submitButton;
	}
}
