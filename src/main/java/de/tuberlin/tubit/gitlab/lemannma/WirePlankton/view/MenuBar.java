package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;

import java.util.List;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.ViewController;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 *
 * @author Stefan Pawlowski
 *
 */
public class MenuBar extends HBox{

	private List<MenuButton> thisMenuButtons;

	/**
	 *
	 */
	public MenuBar(){
		this.thisMenuButtons = ViewController.getMenuItemList();
		this.getChildren().addAll(this.thisMenuButtons);

		this.setHeight(100); //Todo Compute Size



	}

}
