package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;


import java.net.InetAddress;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class SettingsView extends HBox{

	private Button submitButton;

	public SettingsView(){
		super();
		this.submitButton = new Button("Start");

		this.getChildren().add(submitButton);

		submitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				try {
					InetAddress address = InetAddress.getLocalHost();
					MainController.capturePacket(20, 300000, address);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	public Button getSubmitButton() {
		return submitButton;
	}



}
