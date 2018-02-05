package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs;

import java.util.ArrayList;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;
import javafx.scene.control.TextField;

public class NumberField extends TextField{

	private Setting setting;

	public NumberField(Setting setting){
		this.setting = setting;
		this.setText(setting.getActive().get(0));
	}

	@Override public void replaceText(int start, int end, String text) {
        if (text.matches("[0-9]*")) {
          super.replaceText(start, end, text);
          ArrayList<String> a = new ArrayList<String>();
          a.add(text);
          setting.setActive(a);
        }
      }

      @Override public void replaceSelection(String text) {
        if (text.matches("[0-9]*")) {
          super.replaceSelection(text);
          ArrayList<String> a = new ArrayList<String>();
          a.add(text);
          setting.setActive(a);
        }
      }

}
