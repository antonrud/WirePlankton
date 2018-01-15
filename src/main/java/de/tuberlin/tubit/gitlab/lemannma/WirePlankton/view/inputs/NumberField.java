package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;
import javafx.scene.control.TextField;

public class NumberField extends TextField{
	
	Setting setting;
	
	public NumberField(Setting setting){
		this.setting = setting;
	}
	
	@Override public void replaceText(int start, int end, String text) {
        if (text.matches("[0-9]*")) {
          super.replaceText(start, end, text);
          
        }
      }

      @Override public void replaceSelection(String text) {
        if (text.matches("[0-9]*")) {
          super.replaceSelection(text);
        }
      }
	
}
