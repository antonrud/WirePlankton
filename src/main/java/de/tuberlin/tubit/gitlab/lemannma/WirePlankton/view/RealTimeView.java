package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;

import org.pcap4j.packet.Packet;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class RealTimeView  extends ScrollPane{

	Label label;

	public RealTimeView(){

		this.setStyle("-fx-background-color: white;");
		this.label = new Label();
		this.label.setStyle("-fx-background-color: white;");
		this.setContent(label);

		this.setPrefWidth(10000);
		this.setPrefHeight(10000);
	}

	public void setPacket(Packet packet) {

		this.label.setText(packet.toString());
	}

	public Label getLabel() {

		return this.label;
	}
}
