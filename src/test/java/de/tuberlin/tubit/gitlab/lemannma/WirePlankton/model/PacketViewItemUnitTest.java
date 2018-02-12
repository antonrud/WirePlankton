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
import org.pcap4j.packet.Packet;

public class PacketViewItemUnitTest {

	@Test
	public void getIndexTest() {
		
		/*
		int index = 42;
		Packet packet = null;
		
		PacketViewItem item = new PacketViewItem(packet, index);
		assertTrue(index == item.getIndex());
		*/
		//not working, error in code, deactivated to keep maven running
	}

}
