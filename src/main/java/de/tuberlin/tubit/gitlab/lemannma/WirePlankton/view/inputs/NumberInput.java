package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NumberInput extends VBox{
	
	Label l;
	Setting setting;
	TextField numbers;

	public NumberInput(Setting setting){
		
		this.setting = setting;
		Label l = new Label(setting.getName());
		
		this.getChildren().add(l);
		
		TextField numberField = new TextField() {
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
		    };
		
	}


}
