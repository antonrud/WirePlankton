package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PacketView extends VBox {

	public PacketView(){
		this.setStyle("background-color: yellow;");

		generatePacketList();
	}

	public void generatePacketList() {
		this.getChildren().clear();
		for(int iItem=0; iItem < MainController.getPacketList().size();iItem++){
			this.getChildren().add(new PacketViewItem(MainController.getPacketList().get(iItem), iItem));
		}
	}


}
