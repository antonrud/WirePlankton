/**
 * 
 */
package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.Test;
import org.pcap4j.packet.Packet;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;

/**
 * @author Anton Rudacov
 *
 */
public class MainControllerUnitTest {

	/*
	 * instance of test class
	 */
	MainController mainController = new MainController();
	
	/**
	 * Test method for
	 * {@link de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController#capturePacket(int, int)}.
	 */
	@Test
	public void CapturePacketTest() {
		
		int amount;
		int timeout;
		InetAddress address;
		assertTrue(true); // Test for Exception by unvalid Adress
	}
	
	@Test
	public void StopCaptureTest() {
		
		assertTrue(true); // Nothing to Test yet, cause no side effects
	}
	
	@Test
	public void exportDataTest() {
		
		assertTrue(true); // Not implemented yes
	}

	@Test
	public void safeTest() {
		
		assertTrue(true); //  Not implemented yes
	}

	@Test
	public void loadTest() {
		
		assertTrue(true); //  Not implemented yes
	}

	@Test
	public void getSettingsTest() {
		
		assertTrue(true); // TODO
	}

	@Test
	public void getSettingTest() {
		
		assertTrue(true); // TODO
	}

	@Test
	public void setSettingTest() {
		
		assertTrue(true); // TODO
	}

	@Test
	public void initAppTest() {
		
		assertTrue(true); // TODO
	}

	@Test
	public void addPacketTest() {
		
		assertTrue(true); // TODO
	}

	@Test
	public void getPacketListTest() {
		
		assertTrue(true); // TODO
	}
}
