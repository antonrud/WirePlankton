package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener;

import java.io.File;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StartLoadEvent  <T extends Event> implements EventHandler<T>{

	@Override
	public void handle(Event event) {

		Stage s = new Stage();
		FileChooser fileChooser = new FileChooser();

		//Filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Pcap files (*.pcap)", "*.pcap");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.initialFileNameProperty().set("WirePlanktonSave.pcap");

		File f = fileChooser.showOpenDialog(s);

		if(f != null){

			MainController.doLoad(f);
		}

	}

}
