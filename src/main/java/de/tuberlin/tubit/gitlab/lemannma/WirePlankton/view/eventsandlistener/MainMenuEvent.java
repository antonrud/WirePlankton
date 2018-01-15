package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.ViewController;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.MenuButton;
import javafx.event.Event;
import javafx.event.EventHandler;

public class MainMenuEvent<T extends Event> implements EventHandler<T> {

	private int view;

	public MainMenuEvent(int view) {
		this.view = view;
	}
	@Override
	public void handle(Event event) {
		ViewController.changeView(view);

	}

}
