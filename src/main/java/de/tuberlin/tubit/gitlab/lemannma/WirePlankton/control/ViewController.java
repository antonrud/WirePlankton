package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.MenuBar;
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

	public void start(Stage ps) {

		root = new BorderPane();
		ps.setTitle("TrackMan 0.1");
		s = new Scene(root, 800, 600);
		masterWidth = s.getWidth();
		masterHeight = s.getHeight();

		ps.setScene(s);

		root.setRight(new RealTimeView());
		root.setTop(new MenuBar());
		root.setLeft(new PacketView());

		ps.show();
	}


	public static void reload(){
		//TODO Method stub
	}

}
