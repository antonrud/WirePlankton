package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;

import java.util.LinkedList;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.ViewController;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.ViewController.ViewModes;
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

		for (ViewModes viewMode : ViewController.ViewModes.values()){
			thisMenuButtons.add(new MenuButton(viewMode.ordinal(), viewMode.toString()));
		}
		this.getChildren().addAll(this.thisMenuButtons);
	}	
}
