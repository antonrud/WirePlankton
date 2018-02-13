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

import org.junit.Test;
import org.pcap4j.core.PcapNativeException;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.PacketItem;
import javafx.collections.ObservableList;

public class ImportExportControllerUnitTest {

	@Test
	public void exportTest() throws Exception{
		
		//init
		String path ="Test";
		ImportExportController importExportController = new ImportExportController(path);
		ObservableList<PacketItem> packetList = MainController.getPacketList();
		importExportController.doSave(packetList);
	}

	@Test
	public void importTest() throws Exception{
		
		//init
		String path ="test.pcap";
		ImportExportController importExportController = new ImportExportController(path);
		importExportController.doLoad();
	}
	
	@Test
	public void importWithNotValidPathTest(){
		
		//init
		try {
			String path ="//test.pcap";
			ImportExportController importExportController = new ImportExportController(path);
			importExportController.doLoad();
			assertTrue(false);
		}
		catch(PcapNativeException exception) {
			assertTrue(true);
		}
		catch(Exception exception) {
			assertTrue(false);
		}
	}
	
	@Test
	public void CSVExportTest() throws Exception{
		
		//init
		String path ="test";
		ImportExportController importExportController = new ImportExportController(path);
		ObservableList<PacketItem> packetList = MainController.getPacketList();
		importExportController.doCSVExport(packetList);
	}
}
