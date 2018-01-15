package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;

import java.util.LinkedList;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.ViewController;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.ViewController.ViewModes;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener.MainMenuEvent;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;

/**
 *
 * @author Stefan Pawlowski
 *
 */
public class MenuBar extends HBox{

	private LinkedList<MenuButton> thisMenuButtons;

	/**
	 *
	 */
	public MenuBar(LinkedList<MenuButton> buttons){

		this.thisMenuButtons = buttons;

		this.setStyle("-fx-background-color: white");
		this.getChildren().addAll(this.thisMenuButtons);
	}
}
