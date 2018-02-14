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

import static org.junit.Assert.*;

import org.junit.Test;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.Packet.Builder;
import org.pcap4j.packet.Packet.Header;

public class PacketViewItemUnitTest {

	@Test
	public void toStringTest() throws Exception {

		// init PacketItem
		PcapHandle handle = Pcaps.openOffline("test.pcap");
		Packet testPacket = handle.getNextPacketEx();
		PacketItem packetItem = new PacketItem(testPacket);

		// doTest
		String compareString = "PacketItem [Captured at: " + packetItem.getCapturedAt() + ", " + "Original length: "
				+ packetItem.getOriginalLength() + ", Destination address: " + packetItem.getDestinationAddress()
				+ ", Source address: " + packetItem.getSourceAddress() + ", Packet type: " + packetItem.getPacketType()
				+ "]" + ", IP version: " + packetItem.getIpVersion();
		assertTrue(packetItem.toString().contentEquals(compareString));
	}

	@Test
	public void toCSVFormatTest() throws Exception {

		// init PacketItem
		PcapHandle handle = Pcaps.openOffline("test.pcap");
		Packet testPacket = handle.getNextPacketEx();
		PacketItem packetItem = new PacketItem(testPacket);

		// doTest
		String compareString = packetItem.getIndex() + ";" + packetItem.getCapturedAt() + ";"
				+ packetItem.getOriginalLength() + ";" + packetItem.getDestinationAddress() + ";"
				+ packetItem.getSourceAddress() + ";" + packetItem.getPacketType() + packetItem.getIpVersion();
		assertTrue(packetItem.toCSVFormat().contentEquals(compareString));
	}

	@Test
	public void setGetCapturedAtTest() throws Exception {

		// init PacketItem
		PcapHandle handle = Pcaps.openOffline("test.pcap");
		Packet testPacket = handle.getNextPacketEx();
		PacketItem packetItem = new PacketItem(testPacket);

		// doTest
		String value = "value";
		packetItem.setCapturedAt(value);
		assertTrue(packetItem.getCapturedAt().contentEquals(value));
	}

}
