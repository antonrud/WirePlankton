/*
 * Copyright (c) 2017-2018 Anton Rudacov, Stefan Pawlowski, Matthias Lehmann, Svetlana Lepikhine
 *
 * WirePlankton
 * A small network traffic analyzer.
 * 
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
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
          a.add(super.getText());
          setting.setActive(a);
        }
      }

      @Override public void replaceSelection(String text) {
        if (text.matches("[0-9]*")) {
          super.replaceSelection(text);
          ArrayList<String> a = new ArrayList<String>();
          a.add(super.getText());
          setting.setActive(a);
        }
      }

}
