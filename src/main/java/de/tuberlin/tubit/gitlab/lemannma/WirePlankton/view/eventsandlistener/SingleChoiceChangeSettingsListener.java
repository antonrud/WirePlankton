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

import java.util.ArrayList;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class SingleChoiceChangeSettingsListener<T> implements ChangeListener<T>{
	Setting s;

	public SingleChoiceChangeSettingsListener(Setting s){
		super();
		this.s = s;
	}

	@Override
	public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
		ArrayList<String> arr = new ArrayList<String>();
		arr.add(s.getChoices().get((int)(Object)newValue));
		s.setActive(arr);
	}

}
