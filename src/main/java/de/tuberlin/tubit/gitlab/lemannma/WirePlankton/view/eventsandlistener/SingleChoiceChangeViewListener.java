package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.eventsandlistener;

import java.util.ArrayList;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.ViewController;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class SingleChoiceChangeViewListener<T> implements ChangeListener<T>{

	public SingleChoiceChangeViewListener(){
		super();
	}

	@Override
	public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
		ViewController.getPacketview().reloadTable();
	}

}
