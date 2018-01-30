package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;

import java.io.File;

import org.pcap4j.packet.Packet;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

public class RealTimeView  extends ScrollPane{

	Label label;

	public RealTimeView(){

		this.label = new Label();
		this.setContent(label);

		this.setPrefWidth(10000);
		this.setPrefHeight(10000);

		File css = new File("styles/live.css");
		this.getStyleClass().add("background");
		this.getStylesheets().add("file:///"+css.getAbsolutePath().replace("\\", "/"));
		this.label.getStyleClass().add("liveView");
		this.label.getStylesheets().add("file:///"+css.getAbsolutePath().replace("\\", "/"));
	}

	public void setPacket(Packet packet) {

		this.label.setText(packet.toString());
	}

	public Label getLabel() {

		return this.label;
	}
}
