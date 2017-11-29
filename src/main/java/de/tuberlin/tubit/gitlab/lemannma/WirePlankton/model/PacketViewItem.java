package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model;

import org.pcap4j.packet.Packet;

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
