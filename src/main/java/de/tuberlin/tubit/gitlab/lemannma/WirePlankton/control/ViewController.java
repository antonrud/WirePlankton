package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import java.util.LinkedList;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.ExportView;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.MenuBar;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.MenuButton;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.PacketView;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.RealTimeView;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.SettingsView;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener.MainMenuEvent;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener.StartCaptureEvent;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener.StartExportEvent;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener.StartSaveEvent;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener.StartLoadEvent;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;


public class ViewController extends Application {

	//Init basic elements
	private static BorderPane root;
	private static GridPane currentView;
	private static Scene scene;

	private static MenuBar menuBar;
	private static MenuBar settingsBar;
	private static MenuBar dataBar;
	private static PacketView packetView;
	private static ExportView exportView;
	private static RealTimeView realtimeView;
	private static SettingsView settingsView;

	//control of height and width
	private static double masterWidth = 800;
	private static double masterHeight = 600;

	//TODO Enums sollten eigentlich CAPS sein, dann muss ich aber eine extraliste fuer die Menuenamen pflegen..
	public static enum ViewModes{
		Liveview, Settings, File, Statistics
	};

	public static enum captureModes{
		Start
	};

	public static enum dataModes{
		Export, Save, Load
	};

	public void start(Stage primaryStage) {

		//Init Elements
		LinkedList<MenuButton> mainMenu = new LinkedList<MenuButton>();
		for (ViewModes viewMode : ViewController.ViewModes.values()){
			mainMenu.add(new MenuButton(viewMode.toString(), new MainMenuEvent<ActionEvent>(viewMode.ordinal())));
		}
		menuBar = new MenuBar(mainMenu);
		LinkedList<MenuButton> settingsMenu = new LinkedList<MenuButton>();
		for (captureModes sMode : ViewController.captureModes.values()){
			settingsMenu.add(new MenuButton(sMode.toString(), new StartCaptureEvent<ActionEvent>()));
		}
		settingsBar = new MenuBar(settingsMenu);

		LinkedList<MenuButton> dataMenu = new LinkedList<MenuButton>();
		dataMenu.add(new MenuButton(dataModes.Save.toString(), new StartSaveEvent<ActionEvent>()));
		dataMenu.add(new MenuButton(dataModes.Load.toString(), new StartLoadEvent<ActionEvent>()));
		dataMenu.add(new MenuButton(dataModes.Export.toString(), new StartExportEvent<ActionEvent>()));


		dataBar = new MenuBar(dataMenu);


		packetView = new PacketView();
		exportView = new ExportView();
		realtimeView = new RealTimeView();
		settingsView = new SettingsView();

		//Init Window
		root = new BorderPane();
		currentView = new GridPane();
		RowConstraints rc = new RowConstraints();
		rc.setPercentHeight(100);
		rc.setValignment(VPos.TOP);
		currentView.getRowConstraints().add(rc);
		currentView.setPrefSize(10000, 10000);

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
		root.setTop(menuBar);
		root.setCenter(currentView);
		currentView.getColumnConstraints().clear();
		currentView.getChildren().clear();
		int col;
		if(view == 0){
			col = 2;
		} else{
			col = 1;
		}

		for (int i=0;i<col;i++){
			ColumnConstraints cc = new ColumnConstraints();
	        cc.setHalignment(HPos.LEFT);
	        cc.setPercentWidth(100/col);
	        currentView.getColumnConstraints().add(cc);
		}
		switch (view){
			case 0:
				currentView.add(packetView, 0, 0);
				currentView.add(realtimeView, 1, 0);
				break;
			case 1:
				currentView.add(settingsView, 0, 0);
				root.setBottom(settingsBar);
				break;
			case 2:
				currentView.add(exportView, 0, 0);
				root.setBottom(dataBar);
				break;
			case 3:
				break;
			default:
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
