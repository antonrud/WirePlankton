/*
 * Copyright (c) 2017-2018 Anton Rudacov, Stefan Pawlowski, Matthias Lehmann, Svetlana Lepikhine
 *
 * WirePlankton
 * A small network traffic analyzer.
 * 
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.IpV6Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.pcap4j.packet.UdpPacket;

/**
 * This class instantiates PacketItem objects and facilitate conversion to CSV.
 *
 * @author Stefan Pawlowski
 * @author Anton Rudacov
 */
public class PacketItem {

	/** This field is used to generate the packet indexes. */
	private static int indexGen = 0;

	/** The index of the PacketItem. */
	private int index;

	/** The dumped packet. */
	private Packet packet;

	/** The preview of the packet as String. */
	private String preview;

	/** The capture time stamp of the packet. */
	private String capturedAt;

	/** The original length of the packet. */
	private String originalLength;

	/** The destination address of the packet. */
	private String destinationAddress;

	/** The source address of the packet. */
	private String sourceAddress;

	/** The version of IP protocol of the packet. */
	private String packetType;

	/** The version of IP protocol of the packet. */
	private String ipVersion;

	/**
	 * Instantiates a new PacketItem.
	 *
	 * @param packet
	 *            the dumped packet
	 */
	public PacketItem(Packet packet) {

		this.index = ++indexGen;

		this.packet = packet;
		this.preview = packet.toString().replaceAll("\n", "").replaceAll("\r", "");

		// String[] splited = packet.toString().split("\\s*\\r?\\n\\s*");
		// this.capturedAt = splited[0].substring(24, 32);
		// this.originalLength = splited[1].substring(17, splited[1].length());
		// this.destinationAddress = splited[3].substring(21, splited[3].length());
		// this.sourceAddress = splited[4].substring(16, splited[4].length());
		// this.packetType = splited[5].substring(14, splited[5].length() - 1);

		this.capturedAt = DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now());
		this.originalLength = "" + packet.length();
		this.destinationAddress = packet.get(EthernetPacket.class).getHeader().getDstAddr().toString();
		this.sourceAddress = packet.get(EthernetPacket.class).getHeader().getSrcAddr().toString();

		if (packet.get(TcpPacket.class) == null) {
			if (packet.get(UdpPacket.class) == null) {
				this.packetType = "other";
			} else {
				this.packetType = "UDP";
			}
		} else {
			this.packetType = "TCP";
		}

		if (packet.get(IpV4Packet.class) == null) {
			if (packet.get(IpV6Packet.class) == null) {
				this.ipVersion = "other";
			} else {
				this.ipVersion = "IP6";
			}
		} else {
			this.ipVersion = "IP4";
		}
	}

	/**
	 * This method generates CSV lines with packet informations for export.
	 *
	 * @return the CSV line
	 */
	public String toCSVFormat() {
		return index + ";" + capturedAt + ";" + originalLength + ";" + destinationAddress + ";" + sourceAddress + ";"
				+ packetType + ";" + ipVersion;
	}

	/**
	 * Gets the captured time.
	 *
	 * @return the captured time
	 */
	public String getCapturedAt() {
		return capturedAt;
	}

	/**
	 * Sets the captured time.
	 *
	 * @param capturedAt
	 *            the new captured time
	 */
	public void setCapturedAt(String capturedAt) {
		this.capturedAt = capturedAt;
	}

	/**
	 * Gets the original length of the packet.
	 *
	 * @return the original length of the packet
	 */
	public String getOriginalLength() {
		return originalLength;
	}

	/**
	 * Sets the original length of the packet.
	 *
	 * @param originalLength
	 *            the new original length of the packet
	 */
	public void setOriginalLength(String originalLength) {
		this.originalLength = originalLength;
	}

	/**
	 * Gets the destination address of the packet.
	 *
	 * @return the destination address of the packet
	 */
	public String getDestinationAddress() {
		return destinationAddress;
	}

	/**
	 * Sets the destination address of the packet.
	 *
	 * @param destinationAddress
	 *            the new destination address of the packet
	 */
	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	/**
	 * Gets the source address of the packet.
	 *
	 * @return the source address of the packet
	 */
	public String getSourceAddress() {
		return sourceAddress;
	}

	/**
	 * Sets the source address of the packet.
	 *
	 * @param sourceAddress
	 *            the new source address of the packet
	 */
	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}

	/**
	 * Gets the type of the packet.
	 *
	 * @return the type of the packet
	 */
	public String getPacketType() {
		return packetType;
	}

	/**
	 * Sets the type of the packet.
	 *
	 * @param packetType
	 *            the type of the packet
	 */
	public void setPacketType(String packetType) {
		this.packetType = packetType;
	}

	public String getIpVersion() {
		return ipVersion;
	}

	/**
	 * Sets the version of IP protocol of the packet.
	 *
	 * @param packetType
	 *            the version of IP protocol of the packet
	 */
	public void setIpVersion(String ipVersion) {
		this.ipVersion = ipVersion;
	}

	/**
	 * Sets the index of the packet.
	 *
	 * @param index
	 *            the new index of the packet
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Gets the index of the packet.
	 *
	 * @return the index of the packet
	 */
	public int getIndex() {

		return index;
	}

	/**
	 * Gets the preview of the packet.
	 *
	 * @return the preview of the packet
	 */
	public String getPreview() {

		return preview;
	}

	/**
	 * Gets the dumped packet.
	 *
	 * @return the dumped packet
	 */
	public Packet getP() {

		return packet;
	}

	/**
	 * Resets indexGen field.
	 */
	public static void resetIndexGen() {
		indexGen = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PacketItem [Captured at: " + capturedAt + ", Original length: " + originalLength
				+ ", Destination address: " + destinationAddress + ", Source address: " + sourceAddress
				+ ", Packet type: " + packetType + ", IP version: " + ipVersion + "]";
	}
}
