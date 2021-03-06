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

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController;
import javafx.event.Event;
import javafx.event.EventHandler;

public class StartCaptureEvent<T extends Event> implements EventHandler<T> {

	public StartCaptureEvent() {
		super();
	}

	@Override
	public void handle(Event event) {

		MainController.closeHandle();
		try {
			MainController.capturePacket();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
