package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController;
import javafx.event.Event;
import javafx.event.EventHandler;

public class StopCaptureEvent<T extends Event> implements EventHandler<T> {

	public StopCaptureEvent() {
		super();
	}

	@Override
	public void handle(Event event) {

		//MainController.stopCapture();
		MainController.closeHandle();
	}
}
