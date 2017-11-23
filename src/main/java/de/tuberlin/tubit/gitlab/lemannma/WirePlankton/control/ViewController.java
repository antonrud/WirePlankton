package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import java.util.List;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.MenuBar;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.MenuButton;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.PacketView;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.RealTimeView;

import javafx.scene.control.Button;
import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class ViewController extends Application {


	private static BorderPane root;
	private static Scene s;

	//control of height and width
	private static double masterWidth;
	private static double masterHeight;

	private static List<MenuButton> menuItemList;

	public void start(Stage ps) {

		//Basic properties
		root = new BorderPane();
		ps.setTitle("Wireplankton v0.1");
		s = new Scene(root, 800, 600);
		masterWidth = s.getWidth();
		masterHeight = s.getHeight();
		ps.setScene(s);

		//Populate Menu


		//Layout
		//TODO Dynamic layouts
		root.setRight(new RealTimeView());
		root.setTop(new MenuBar());
		root.setLeft(new PacketView());

		ps.show();
	}


	public static void reload(){
		//TODO Method stub
	}


	public static void go() {
		ViewController.launch();

	}


	public static List<MenuButton> getMenuItemList() {
		return menuItemList;

	}

}
