package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener;


import java.io.File;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.ViewController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StartExportEvent <T extends Event> implements EventHandler<T> {

	public StartExportEvent() {

	}


	@Override
	public void handle(Event event) {

		Stage s = new Stage();
		FileChooser fileChooser = new FileChooser();

		//Filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Pcap files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.initialFileNameProperty().set("WirePlanktonExport.csv");

		File f = fileChooser.showSaveDialog(s);
		if(!f.getAbsolutePath().endsWith(".csv")){
			f = new File(f.getAbsolutePath()+".csv");
		}
		if(f != null){

			MainController.doCSVExport(f);
		}

	}

}