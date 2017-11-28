package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;

import org.pcap4j.packet.Packet;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class RealTimeView  extends VBox{

	Label text;

	public RealTimeView(){
		this.setStyle("background-color: red;");
		this.text = new Label();
		this.getChildren().add(text);
	}

	public void setPacket(Packet p) {
		this.text.setText(p.toString());

	}

}
