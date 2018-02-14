/*
 * Copyright (c) 2017-2018 Anton Rudacov, Stefan Pawlowski, Matthias Lehmann, Svetlana Lepikhine
 *
 * WirePlankton
 * A small network traffic analyzer.
 * 
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener;

import java.io.File;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StartSaveEvent<T extends Event> implements EventHandler<T> {

	public StartSaveEvent() {

	}

	// @Override
	// public void handle(Event event) {
	//
	// Stage s = new Stage();
	// FileChooser fileChooser = new FileChooser();
	//
	// // Filter
	// FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Pcap
	// files (*.pcap)", "*.pcap");
	// fileChooser.getExtensionFilters().add(extFilter);
	// fileChooser.initialFileNameProperty().set("WirePlanktonSave.pcap");
	//
	// File f = fileChooser.showSaveDialog(s);
	//
	// if (!f.getAbsolutePath().endsWith(".pcap")) {
	// f = new File(f.getAbsolutePath() + ".pcap");
	//
	// if (f != null) {
	//
	// MainController.doSave(f);
	// }
	// }
	//
	// }

	@Override
	public void handle(Event event) {

		Stage s = new Stage();
		FileChooser fileChooser = new FileChooser();

		// Filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Pcap files (*.pcap)", "*.pcap");
		fileChooser.getExtensionFilters().add(extFilter);
		fileChooser.initialFileNameProperty().set("WirePlanktonSave.pcap");

		File f = fileChooser.showSaveDialog(s);
		if (!f.getAbsolutePath().endsWith(".pcap")) {
			f = new File(f.getAbsolutePath() + ".pcap");
		}

		if (f != null) {

			MainController.doSave(f);
		}

	}

}
