package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener;

import java.net.InetAddress;
import java.net.UnknownHostException;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.ViewController;
import javafx.event.Event;
import javafx.event.EventHandler;

public class StartCaptureEvent<T extends Event> implements EventHandler<T> {

	public StartCaptureEvent() {
		super();
	}
	@Override
	public void handle(Event event) {
		try {
			MainController.capturePacket(0, 100000, 2000000, InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
