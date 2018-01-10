package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;

import org.pcap4j.packet.Packet;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class RealTimeView  extends VBox{

	Label label;

	public RealTimeView(){
		
		this.setStyle("-fx-background-color: white;");
		this.label = new Label();
		this.getChildren().add(label);
	}

	public void setPacket(Packet packet) {
	
		this.label.setText(packet.toString());
	}

	public Label getLabel() {
		
		return this.label;
	}
}
