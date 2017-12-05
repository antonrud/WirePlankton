package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model;

import org.pcap4j.packet.Packet;

public class PacketViewItem{

	int index;
	String preview;
	Packet packet;

	public PacketViewItem(Packet packet, int index){

		this.packet = packet;
		this.index = index;
		this.preview = packet.toString().replaceAll("\n", "").replaceAll("\r", "");
	}

	public int getIndex() {
	
		return index;
	}

	public String getPreview() {
		
		return preview;
	}

	public Packet getP() {
		
		return packet;
	}



}
