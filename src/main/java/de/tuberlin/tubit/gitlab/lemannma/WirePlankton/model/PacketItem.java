package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model;

import org.pcap4j.packet.Packet;

/**
 * Diese Klasse so f�r die Exporte nutzen und die PacketCSV Klasse l�schen
 * 
 * @author Stef
 *
 */
public class PacketItem {

	private static int indexGen = 0;

	private int index;
	private Packet packet;
	private String preview;
	private String capturedAt;
	private String originalLength;
	private String destinationAddress;
	private String sourceAddress;
	private String packetType;

	public PacketItem(Packet packet) {

		this.index = ++indexGen;

		this.packet = packet;
		this.preview = packet.toString().replaceAll("\n", "").replaceAll("\r", "");
		
		String[] splited = packet.toString().split("\\s*\\r?\\n\\s*");
		this.capturedAt = splited[0].substring(24, 32);
		this.originalLength = splited[1].substring(17, splited[1].length());
		this.destinationAddress = splited[3].substring(21, splited[3].length());
		this.sourceAddress = splited[4].substring(16, splited[4].length());
		this.packetType = splited[5].substring(14, splited[5].length() - 1);
	}

	public String toCSVFormat() {
		return index + ";" + capturedAt + ";" + originalLength + ";" + destinationAddress + ";" + sourceAddress + ";"
				+ packetType;
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

	public void setIndex(int index) {
		this.index = index;
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

	public static void resetIndexGen() {
		indexGen = 0;
	}

	@Override
	public String toString() {
		return "PacketItem [Captured at: " + capturedAt + ", Original length: " + originalLength
				+ ", Destination address: " + destinationAddress + ", Source address: " + sourceAddress
				+ ", Packet type: " + packetType + "]";
	}
}
