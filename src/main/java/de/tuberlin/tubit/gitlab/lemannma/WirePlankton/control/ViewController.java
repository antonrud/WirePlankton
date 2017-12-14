package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.ExportView;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.MenuBar;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.PacketView;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.RealTimeView;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.SettingsView;
import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class ViewController extends Application {

	//Init basic elements
	private static BorderPane root;
	private static Scene scene;

	private static MenuBar menuBar;
	private static PacketView packetView;
	private static ExportView exportView;
	private static RealTimeView realtimeView;
	private static SettingsView settingsView;

	//control of height and width
	private static double masterWidth = 800;
	private static double masterHeight = 600;

	//TODO Enums sollten eigentlich CAPS sein, dann muss ich aber eine extraliste f�r die Men�namen pflegen..
	public static enum ViewModes{
		Liveview, Settings, File
	};

	public void start(Stage primaryStage) {

		//Init Elements
		menuBar = new MenuBar();
		packetView = new PacketView();
		exportView = new ExportView();
		realtimeView = new RealTimeView();
		settingsView = new SettingsView();

		//Init Window
		root = new BorderPane();
		primaryStage.setTitle("Wireplankton v0.1");
		scene = new Scene(root, masterWidth, masterHeight);
		primaryStage.setScene(scene);

		//Init StandardView
		changeView(0);

		primaryStage.show();
	}

	public static void go() {

		ViewController.launch();
	}

	public static void changeView(int view) {
		
		root.getChildren().clear();
		switch (view){
			case 0:
				root.setTop(menuBar);
				root.setRight(realtimeView);
				root.setLeft(packetView);
				packetView.generatePacketList();
				break;
			case 1:
				root.setTop(menuBar);
				root.setCenter(settingsView);
				break;
			case 2:
				root.setTop(menuBar);
				root.setCenter(exportView);
				break;
		}
	}

	public static MenuBar getMenubar() {
		
		return menuBar;
	}

	public static PacketView getPacketview() {
		
		return packetView;
	}

	public static ExportView getExportview() {
		
		return exportView;
	}

	public static RealTimeView getRealtimeview() {
		
		return realtimeView;
	}

	public static SettingsView getSettingsview() {
		
		return settingsView;
	}



}
