package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model;

import org.pcap4j.packet.Packet;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.ViewController;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class PacketViewItem{

	int index;
	String preview;
	Packet p;

	public PacketViewItem(Packet p, int index){
		this.p = p;
		this.index = index;
		this.preview = p.toString().replaceAll("\n", "").replaceAll("\r", "");

	}

	public int getIndex() {
		return index;
	}

	public String getPreview() {
		return preview;
	}

	public Packet getP() {
		return p;
	}



}
