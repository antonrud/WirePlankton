package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model;

import org.pcap4j.packet.Packet;

public class PacketViewItem {

	int index;
	Packet packet;
	String preview;

	//TODO Discuss if it's necessary to use "int index" here
	public PacketViewItem(Packet packet, int index) {

		this.index = index;
		this.packet = packet;
		this.preview = packet.toString().replaceAll("\n", "").replaceAll("\r", "");
	}

	public PacketViewItem(Packet packet) {

		this.packet = packet;
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
