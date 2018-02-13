package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener;

import java.io.File;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StartStatExportEvent<T extends Event> implements EventHandler<T> {

	public StartStatExportEvent() {

	}


	@Override
	public void handle(Event event) {

		Stage s = new Stage();
		FileChooser fileChooser = new FileChooser();

		//Filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Pcap files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.initialFileNameProperty().set("WirePlanktonStatExport.txt");

		File f = fileChooser.showSaveDialog(s);
		if(f != null && !f.getAbsolutePath().endsWith(".txt")){
			f = new File(f.getAbsolutePath()+".txt");
		}
		if(f != null){

			MainController.doStatisticsExport(f);
		}

	}

}