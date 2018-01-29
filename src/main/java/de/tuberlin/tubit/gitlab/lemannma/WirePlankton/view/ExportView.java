package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs.MultiChoice;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs.NumberInput;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.inputs.SingleChoice;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ExportView extends ScrollPane{

	private VBox settingsContent;

	public ExportView(){

		super();
		this.settingsContent = new VBox();
		for (Setting setting : MainController.getExportSettings()) {
			switch(setting.getFieldType()){

			case "SINGLECHOICE":
				SingleChoice f = new SingleChoice(setting);
				this.settingsContent.getChildren().add(f);
				break;
			case "MULTICHOICE":
				MultiChoice m = new MultiChoice(setting);
				this.settingsContent.getChildren().add(m);
				break;
			case "NUMBER":
				NumberInput n = new NumberInput(setting);
				this.settingsContent.getChildren().add(n);
				break;
			}
		}
		this.setContent(this.settingsContent);

		//Style
		this.setStyle("-fx-background: white");
		this.settingsContent.setStyle("-fx-background-color: white");
		//File css = new File("styles/settings.css");
		//this.settingsContent.getStyleClass().add("settingsBack");
		//this.settingsContent.getStylesheets().add("file:///"+css.getAbsolutePath().replace("\\", "/"));
	}
}
