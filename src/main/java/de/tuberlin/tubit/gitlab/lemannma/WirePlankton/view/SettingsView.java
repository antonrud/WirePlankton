package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;


import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.CaptureController;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.ViewController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class SettingsView extends HBox{
	
	public SettingsView(){
		super();
		TextField textField = new TextField ();
		Button submitButton = new Button("Start");
		
		submitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				try {
					//MainController.capturePacket(20, 30);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		this.getChildren().add(textField);
		this.getChildren().add(submitButton);
	}

}
