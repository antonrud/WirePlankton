package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model;

import org.pcap4j.packet.Packet;

/**
 * Diese Klasse so für die Exporte nutzen und
 * die PacketCSV Klasse löschen
 * @author Stef
 *
 */
public class PacketViewItem {

	private int index;
	private static int indexGen = 0;
	private Packet packet;
	private String preview;
	private String capturedAt;
	private String originalLength;
	private String destinationAddress;
	private String sourceAddress;
	private String packetType;

	public PacketViewItem(Packet packet) {

		this.packet = packet;
		this.preview = packet.toString().replaceAll("\n", "").replaceAll("\r", "");

		String[] splited = packet.toString().split("\\s*\\r?\\n\\s*");

		this.capturedAt = splited[0];
		this.originalLength = splited[1];
		this.destinationAddress = splited[3];
		this.sourceAddress = splited[4];
		this.packetType = splited[5];

		this.index = indexGen++;
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

	public String toCSVFormat() {
		return index + ";" + capturedAt + ";" + originalLength + ";" + destinationAddress + ";" + sourceAddress + ";"
				+ packetType;
	}

}
