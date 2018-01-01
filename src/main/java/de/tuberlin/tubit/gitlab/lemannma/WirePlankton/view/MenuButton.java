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

	private int view;

	public MenuButton(int view, String label){

		super();
		this.view = view;

		//Set Label
		this.setText(label);

		//Set Integer for viewchange
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				//TODO Okay oder eigenes Event?
				ViewController.changeView(((MenuButton)event.getSource()).getView());
			}
		});

		File css = new File("styles/menu.css");
		this.getStyleClass().add("button");
		this.getStylesheets().add("file:///"+css.getAbsolutePath().replace("\\", "/"));
	}

	public int getView() {

		return view;
	}

}
