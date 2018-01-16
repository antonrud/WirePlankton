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
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Pcap files (*.pcap)", "*.pcap");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.initialFileNameProperty().set("WirePlanktonExport.pcap");

		File f = fileChooser.showSaveDialog(s);
		if(!f.getAbsolutePath().endsWith(".pcap")){
			f = new File(f.getAbsolutePath()+".pcap");
		}
		if(f != null){

			MainController.exportData(f);
		}

	}

}