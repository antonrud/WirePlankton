/*
 * Copyright (c) 2017-2018 Anton Rudacov, Stefan Pawlowski, Matthias Lehmann, Svetlana Lepikhine
 *
 * WirePlankton
 * A small network traffic analyzer.
 * 
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
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
