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
