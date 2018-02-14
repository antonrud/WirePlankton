package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;
/*
import static org.junit.Assert.*;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.ExportView;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.MenuBar;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.PacketView;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.RealTimeView;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.SettingsView;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class ViewControllerIntegrationTest extends ApplicationTest {

	private static BorderPane root;
	private static GridPane currentView;
	private static Scene scene;

	private static MenuBar menuBar;
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
	
	@Override
	public void start(Stage primaryStage) {
		
		//Init Elements
		menuBar = new MenuBar();
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
		ViewController.changeView(0);

		primaryStage.show();
	}
	
	@Test
	public void hasMenuBarTest() {
	        // expect:
	        verifyThat(".MenuBar", hasText("Settings"));
	}
	
    @Test 
    public void SettingsOptionMenuBarTest() {
        // when:
        clickOn(".button");

        // then:
        verifyThat(".button", hasText("Start"));
    }	
}
*/
