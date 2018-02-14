/*
 * Copyright (c) 2017-2018 Anton Rudacov, Stefan Pawlowski, Matthias Lehmann, Svetlana Lepikhine
 *
 * WirePlankton
 * A small network traffic analyzer.
 * 
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import static org.junit.Assert.*;

import java.io.File;
import java.net.InetAddress;
import java.util.LinkedList;

import org.junit.Test;
import org.pcap4j.packet.Packet;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.PacketItem;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;
import javafx.collections.ObservableList;

/**
 * @author Anton Rudacov
 *
 */
public class MainControllerUnitTest {

	Packet mockPacket = new Packet() {
		
		@Override
		public int length() {
			return 1;
		}
		
		@Override
		public byte[] getRawData() {
			return new byte[] {1,2,3};
		}
		
		@Override
		public Packet getPayload() {
			return this;
		}
		
		@Override
		public Header getHeader() {
			return new Header() {
				@Override
				public int length() {
					return 1;
				}

				@Override
				public byte[] getRawData() {
					return new byte[] {1,2,3};
				}			
			};
		}
		
		@Override
		public Builder getBuilder() {
			return new Builder() {
				@Override
				public Builder payloadBuilder(Builder payloadBuilder) {
					return null;
				}

				@Override
				public Builder getPayloadBuilder() {
					return null;
				}

				@Override
				public Packet build() {

					return null;
				}				
			};
		}
	};	

	@Test
	public void getEmptyPacketListTest() {
	
		ObservableList<PacketItem> packetList = MainController.getPacketList();
		MainController.clearPacketList();
		assertTrue(packetList.isEmpty());
	}
	
	@Test
	public void capturePacketTest() throws Exception {
		MainController.initContext();
		WirePlankton.configure();		
		MainController.capturePacket();
		MainController.closeHandle();
		MainController.stopCapture();
	}
	
	@Test
	public void fileTests() {
		File fTest1 = new File("Test");
		File fTest2 = new File("test.pcap");
		WirePlankton.configure();		
		MainController.doCSVExport(fTest1);
		MainController.doSave(fTest1);
		MainController.doLoad(fTest2);
		MainController.doStatisticsExport(fTest1);
	}
	
	@Test
	public void settingsGetterTest( ) {
		WirePlankton.configure();		
		
		LinkedList<Setting> displaySettings = MainController.getDisplaySettingsList();
		assertTrue(displaySettings.get(0).getChoices().size()
				==3);
		
		LinkedList<Setting> statSettings = MainController.getStatSettingsList();
		assertTrue(statSettings.get(0).getChoices().size()==6);
		System.out.println("need to look");
	}
	
	//skip getter for statistics, cause they just make sence in a running context
}
