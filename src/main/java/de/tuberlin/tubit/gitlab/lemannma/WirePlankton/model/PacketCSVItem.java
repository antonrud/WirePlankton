package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model;

import org.pcap4j.packet.Packet;

public class PacketCSVItem {

	int index;
	String capturedAt;
	String originalLength;
	String destinationAddress;
	String sourceAddress;
	String packetType;

	public PacketCSVItem(Packet packet, int index) {
		this.index = index;

		String[] splited = packet.toString().split("\\s*\\r?\\n\\s*");

		this.capturedAt = splited[0];
		this.originalLength = splited[1];
		this.destinationAddress = splited[3];
		this.sourceAddress = splited[4];
		this.packetType = splited[5];
	}

	public PacketCSVItem(String importLine) {
		String[] splited = importLine.split(";");

		this.index = Integer.parseInt(splited[0]);
		this.capturedAt = splited[1];
		this.originalLength = splited[2];
		this.destinationAddress = splited[3];
		this.sourceAddress = splited[4];
		this.packetType = splited[5];
	}

	// TODO Could be useful in the future
	// public PacketCSVItem(String capturedAt, String originalLength, String
	// destinationAddress, String sourceAddress, String packetType, int packetNr) {
	// this.packetNr = packetNr;
	// this.capturedAt = capturedAt;
	// this.originalLength = originalLength;
	// this.destinationAddress = destinationAddress;
	// this.sourceAddress = sourceAddress;
	// this.packetType = packetType;
	// }

	public String toCSVFormat() {
		return index + ";" + capturedAt + ";" + originalLength + ";" + destinationAddress + ";" + sourceAddress + ";"
				+ packetType;
	}

	@Override
	public String toString() {
		return "PacketItem [Captured at: " + capturedAt + ", Original length: " + originalLength
				+ ", Destination address: " + destinationAddress + ", Source address: " + sourceAddress
				+ ", Packet type: " + packetType + "]";
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getCapturedAt() {
		return capturedAt;
	}

	public void setCapturedAt(String capturedAt) {
		this.capturedAt = capturedAt;
	}

	public String getOriginalLength() {
		return originalLength;
	}

	public void setOriginalLength(String originalLength) {
		this.originalLength = originalLength;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getSourceAddress() {
		return sourceAddress;
	}

	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}

	public String getPacketType() {
		return packetType;
	}

	public void setPacketType(String packetType) {
		this.packetType = packetType;
	}
}
