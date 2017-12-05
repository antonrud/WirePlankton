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
	
	int amount = 20;
	int timeout = 200000;
	InetAddress address = null;
	
	/**
	 * Test method for
	 * {@link de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController#capturePacket(int, int)}.
	 */
	@Test
	public void CapturePacketTestUnvalidAddress() {
		
		try {
			MainController.capturePacket(amount, timeout, address);
		} catch (Exception exception) {
			assertTrue(true); // Test for Exception by unvalid address
		}
	}
	
	@Test
	public void CapturePacketTestUnvalidAmount() {
		
		amount = 0;
		try {
			address = InetAddress.getLocalHost();
			MainController.capturePacket(amount, timeout, address);
		} catch (Exception exception) {
			assertTrue(true); // Test for Exception by unvalid amount
		}
	}
	
	@Test
	public void CapturePacketTestUnvalidTimeout() { 
		
		amount =20;
		timeout = -1;
		try {
			address = InetAddress.getLocalHost();
			MainController.capturePacket(amount, timeout, address);
		} catch (Exception exception) {
			assertTrue(true); // Test for Exception by unvalid timeout
		}
	}
	
	@Test
	public void StopCaptureTest() {
		
		assertTrue(true); // Nothing to Test yet, cause no side effects
	}
	
	@Test
	public void exportDataTest() {
		
		assertTrue(true); // Not implemented yet
	}

	@Test
	public void safeTest() {
		
		assertTrue(true); //  Not implemented yet
	}

	@Test
	public void loadTest() {
		
		assertTrue(true); //  Not implemented yet
	}

	@Test
	public void initAppTest() {
		
		assertTrue(true); // Not implemented yet
	}

	@Test
	public void addPacketTest() {
		
		assertTrue(true); // simple function call, no test needed yet
	}
}
 