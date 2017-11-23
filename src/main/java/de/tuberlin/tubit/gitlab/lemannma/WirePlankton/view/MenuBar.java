package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.ViewController;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.ViewController.ViewModes;
import javafx.scene.control.Button;
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
	public MenuBar(){
		this.thisMenuButtons = new LinkedList<MenuButton>();
		
		for (ViewModes viewmode : ViewController.ViewModes.values()){
			thisMenuButtons.add(new MenuButton(viewmode.ordinal(), viewmode.toString()));
		}
		this.getChildren().addAll(this.thisMenuButtons);

		this.setHeight(100); //Todo Compute Size



	}

}
