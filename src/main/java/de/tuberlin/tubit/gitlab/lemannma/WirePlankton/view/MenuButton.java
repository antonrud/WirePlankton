package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;

import java.io.File;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.ViewController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 *
 * @author Stef
 *
 */
public class MenuButton extends Button {

	public MenuButton(String label, EventHandler<ActionEvent> event){

		super();

		//Set Label
		this.setText(label);

		//Set Integer for viewchange
		this.setOnAction(event);

		File css = new File("styles/menu.css");
		this.getStyleClass().add("button");
		this.getStylesheets().add("file:///"+css.getAbsolutePath().replace("\\", "/"));
	}

}
