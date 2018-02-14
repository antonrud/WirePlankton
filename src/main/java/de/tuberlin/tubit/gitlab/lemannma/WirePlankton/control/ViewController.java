/*
 * Copyright (c) 2017-2018 Anton Rudacov, Stefan Pawlowski, Matthias Lehmann, Svetlana Lepikhine
 *
 * WirePlankton
 * A small network traffic analyzer.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import java.io.File;
import java.util.LinkedList;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.ExportView;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.MenuBar;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.MenuButton;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.PacketView;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.RealTimeView;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.SettingsBar;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.SettingsView;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.StatisticsView;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener.MainMenuEvent;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener.SingleChoiceChangeStatListener;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener.SingleChoiceChangeViewListener;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener.StartCaptureEvent;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener.StartExportEvent;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener.StartStatExportEvent;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener.StartSaveEvent;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener.StopCaptureEvent;
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

/**
 * This class initializes and controls the GUI.
 *
 * @author Stefan Pawlowski
 */
public class ViewController extends Application {

	/** The layout root. */
	private static BorderPane root;

	/** The current view. */
	private static GridPane currentView;

	/** The scene. */
	private static Scene scene;

	/** The menu bar. */
	private static MenuBar menuBar;

	/** The settings bar. */
	private static MenuBar settingsBar;

	/** The statistics settings bar. */
	private static SettingsBar statSettingsBar;

	/** The view settings bar. */
	private static SettingsBar viewSettingsBar;

	/** The data bar. */
	private static MenuBar dataBar;

	/** The packet view. */
	private static PacketView packetView;

	/** The export view. */
	private static ExportView exportView;

	/** The real-time view for packets. */
	private static RealTimeView realtimeView;

	/** The settings view. */
	private static SettingsView settingsView;

	/** The statistics view. */
	private static StatisticsView statView;

	/** Initial width of window. */
	private static double masterWidth = 800;

	/** Initial height of window. */
	private static double masterHeight = 600;

	/**
	 * The Enum to determinate the next view.
	 */
	public static enum ViewModes {

		Liveview, Settings, File, Statistics
	};

	/**
	 * The Enum for generating capture options.
	 */
	public static enum captureModes {

		Start, Stop
	};

	/**
	 * The Enum for data options.
	 */
	public static enum dataModes {

		Export, Save, Load, ExportStats
	};

	/*
	 * (non-Javadoc)
	 *
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	public void start(Stage primaryStage) {

		// Init Elements
		LinkedList<MenuButton> mainMenu = new LinkedList<MenuButton>();
		for (ViewModes viewMode : ViewController.ViewModes.values()) {
			mainMenu.add(new MenuButton(viewMode.toString(), new MainMenuEvent<ActionEvent>(viewMode.ordinal())));
		}
		menuBar = new MenuBar(mainMenu);

		LinkedList<MenuButton> settingsMenu = new LinkedList<MenuButton>();
		settingsMenu.add(new MenuButton(captureModes.Start.toString(), new StartCaptureEvent<ActionEvent>()));
		settingsMenu.add(new MenuButton(captureModes.Stop.toString(), new StopCaptureEvent<ActionEvent>()));
		settingsBar = new MenuBar(settingsMenu);

		LinkedList<MenuButton> dataMenu = new LinkedList<MenuButton>();
		dataMenu.add(new MenuButton(dataModes.Save.toString(), new StartSaveEvent<ActionEvent>()));
		dataMenu.add(new MenuButton(dataModes.Load.toString(), new StartLoadEvent<ActionEvent>()));
		dataMenu.add(new MenuButton(dataModes.Export.toString(), new StartExportEvent<ActionEvent>()));
		dataMenu.add(new MenuButton(dataModes.ExportStats.toString(), new StartStatExportEvent<ActionEvent>()));

		dataBar = new MenuBar(dataMenu);
		statSettingsBar = new SettingsBar(MainController.getStatSettingsList(), new SingleChoiceChangeStatListener<>());
		viewSettingsBar = new SettingsBar(MainController.getDisplaySettingsList(),
				new SingleChoiceChangeViewListener<>());

		packetView = new PacketView();
		exportView = new ExportView();
		realtimeView = new RealTimeView();
		settingsView = new SettingsView();
		statView = new StatisticsView();

		// Init Window
		root = new BorderPane();
		currentView = new GridPane();
		RowConstraints rc = new RowConstraints();
		rc.setPercentHeight(100);
		rc.setValignment(VPos.TOP);
		currentView.getRowConstraints().add(rc);
		currentView.setPrefSize(10000, 10000);

		primaryStage.setTitle("Wireplankton v1.0.3");
		scene = new Scene(root, masterWidth, masterHeight);
		primaryStage.setScene(scene);

		// Style Panes

		File css = new File("styles/main.css");
		root.getStyleClass().add("background");
		currentView.getStyleClass().add("background");
		root.getStylesheets().add("file:///" + css.getAbsolutePath().replace("\\", "/"));
		root.getStylesheets().add("file:///" + css.getAbsolutePath().replace("\\", "/"));

		// Init StandardView
		changeView(0);

		primaryStage.show();
	}

	/**
	 * Supportfunction to launch controller
	 */
	public static void go() {

		ViewController.launch();
	}

	/**
	 * Change Main View
	 *
	 * @param view
	 *            The View Enum for the next view
	 */
	public static void changeView(int view) {
		root.getChildren().clear();
		root.setTop(menuBar);
		root.setCenter(currentView);
		currentView.getColumnConstraints().clear();
		currentView.getChildren().clear();
		int col;
		if (view == 0) {
			col = 2;
		} else {
			col = 1;
		}

		for (int i = 0; i < col; i++) {
			ColumnConstraints cc = new ColumnConstraints();
			cc.setHalignment(HPos.LEFT);
			cc.setPercentWidth(100 / col);
			currentView.getColumnConstraints().add(cc);
		}
		switch (view) {
		case 0:
			currentView.add(packetView, 0, 0);
			currentView.add(realtimeView, 1, 0);
			root.setBottom(viewSettingsBar);
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
			currentView.add(statView, 0, 0);
			statView.changeStat(0);
			root.setBottom(statSettingsBar);
		default:
			break;
		}

	}

	/**
	 * Gets the menubar.
	 *
	 * @return the menubar
	 */
	public static MenuBar getMenubar() {

		return menuBar;
	}

	/**
	 * Gets the packetview.
	 *
	 * @return the packetview
	 */
	public static PacketView getPacketview() {

		return packetView;
	}

	/**
	 * Gets the exportview.
	 *
	 * @return the exportview
	 */
	public static ExportView getExportview() {

		return exportView;
	}

	/**
	 * Gets the realtimeview.
	 *
	 * @return the realtimeview
	 */
	public static RealTimeView getRealtimeview() {

		return realtimeView;
	}

	/**
	 * Gets the settingsview.
	 *
	 * @return the settingsview
	 */
	public static SettingsView getSettingsview() {

		return settingsView;
	}

	/**
	 * Gets the stat view.
	 *
	 * @return the stat view
	 */
	public static StatisticsView getStatView() {
		return statView;

	}

}
