package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;

import java.io.File;
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
		this.getChildren().addAll(this.thisMenuButtons);

		File css = new File("styles/menu.css");
		this.getStyleClass().add("menu");
		this.getStylesheets().add("file:///"+css.getAbsolutePath().replace("\\", "/"));
	}
}
