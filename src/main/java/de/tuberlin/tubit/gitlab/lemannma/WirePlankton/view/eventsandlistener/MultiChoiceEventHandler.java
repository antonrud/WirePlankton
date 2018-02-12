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

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;

public class MultiChoiceEventHandler<T extends Event> implements EventHandler<T> {

	Setting setting;

	public MultiChoiceEventHandler(Setting setting) {
		this.setting = setting;
	}

	@Override
	public void handle(T event) {
		if (((CheckBox)event.getSource()).isSelected()){
			setting.getActive().add(((CheckBox)event.getSource()).getText());
		} else{
			setting.getActive().remove(setting.getActive().indexOf(((CheckBox)event.getSource()).getText()));
		}

	}

}
