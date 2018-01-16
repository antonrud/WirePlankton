package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener;

import java.util.ArrayList;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class SingleChoiceChangeListener<T> implements ChangeListener<T>{
	Setting s;

	public SingleChoiceChangeListener(Setting s){
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
