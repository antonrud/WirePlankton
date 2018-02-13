//is not working cause of build platform but runs local
///*
// * Copyright (c) 2017-2018 Anton Rudacov, Stefan Pawlowski, Matthias Lehmann, Svetlana Lepikhine
// *
// * WirePlankton
// * A small network traffic analyzer.
// * 
// * This software may be modified and distributed under the terms
// * of the MIT license.  See the LICENSE file for details.
// */
//package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;
//
//import static org.junit.Assert.*;
//
//import org.junit.Test;
//import org.pcap4j.core.PcapHandle;
//import org.pcap4j.core.Pcaps;
//import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
//import org.pcap4j.core.PcapNetworkInterface;
//
///**
// * @author Matthias Lehmann
// *
// */
//public class CaptureControllerUnitTest {
//
//	/**
//	 * @author Matthias
//	 * 
//	 * Test method for
//	 * {@link de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.CaptureControllerUnitTest#stop()}.
//	 */
//	@Test
//	public void testRun() {
//		try {
//			doTestWithWorkingSettings();
//		} catch (Exception exception) {
//			assertTrue(false);
//		}
//		
//		try {
//			doTestWithoutWorkingSettings();
//		} catch (Exception exception) {
//			assertTrue(true);
//		}
//	}
//	
//	void doTestWithWorkingSettings() throws Exception {
//		//init
//		PcapHandle handle = null;
//		int amount = 1;
//		int limit = 1;
//		int timeout = 1;
//		PcapNetworkInterface pcapNetworkInterface = Pcaps.findAllDevs().get(0);
//		handle = pcapNetworkInterface.openLive(65536, PromiscuousMode.PROMISCUOUS, timeout);
//		CaptureController captureController = new CaptureController(handle, amount, limit);
//		
//		//run method
//		doTest(captureController);
//	}
//	
//	void doTestWithoutWorkingSettings() throws Exception {
//		//init
//		PcapHandle handle = null;
//		int amount = 1;
//		int limit = 1;
//		CaptureController captureController = new CaptureController(handle, amount, limit);
//		
//		//run method
//		doTest(captureController);
//	}
//	
//	void doTest(CaptureController captureController) throws Exception {
//		captureController.run();
//		//wait 1 second
//		Thread.sleep(1000);
//	}
//}
