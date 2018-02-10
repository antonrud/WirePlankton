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
		try {
			MainController.capturePacket();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
