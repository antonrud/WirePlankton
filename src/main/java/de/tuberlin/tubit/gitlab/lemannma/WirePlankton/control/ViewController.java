package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import java.util.List;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.ExportView;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.MenuBar;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.MenuButton;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.PacketView;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.RealTimeView;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.SettingsView;
import javafx.scene.control.Button;
import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class ViewController extends Application {


	//Init basic elements
	private static BorderPane root;
	private static Scene s;

	private static MenuBar menubar;
	private static PacketView packetview;
	private static ExportView exportview;
	private static RealTimeView realtimeview;
	private static SettingsView settingsview;


	//control of height and width
	private static double masterWidth;
	private static double masterHeight;

	//TODO Enums sollten eigentlich CAPS sein, dann muss ich aber eine extraliste für die Menünamen pflegen..
	public static enum ViewModes{
		Liveview, Settings, File
	};

	public void start(Stage ps) {

		//Init Elements
		menubar = new MenuBar();
		packetview = new PacketView();
		exportview = new ExportView();
		realtimeview = new RealTimeView();
		settingsview = new SettingsView();

		//Init Window
		root = new BorderPane();
		ps.setTitle("Wireplankton v0.1");
		s = new Scene(root, 800, 600);
		masterWidth = s.getWidth();
		masterHeight = s.getHeight();
		ps.setScene(s);
		
		//Init StandardView
		changeView(0);

		ps.show();
	}

	public static void go() {
		ViewController.launch();

	}


	public static void changeView(int view) {
		root.getChildren().clear();
		switch (view){
			case 0:
				root.setTop(menubar);
				root.setRight(realtimeview);
				root.setLeft(packetview);
				break;
			case 1:
				root.setTop(menubar);
				root.setCenter(settingsview);
			case 2:
				root.setTop(menubar);
				root.setCenter(exportview);

		}

	}

}
