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
