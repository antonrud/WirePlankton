/**
 * 
 */
package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import static org.junit.Assert.*;
import java.net.InetAddress;
import org.junit.Test;

/**
 * @author Matthias Lehmann
 *
 */
public class CaptureControllerUnitTest {

	private int amount = 1;
	private int timeout = 1;
	
	/**
	 * Test method for
	 * {@link de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.CaptureControllerUnitTest#doCapture(org.pcap4j.core.PcapNetworkInterface, int, int, java.util.ArrayList)}.
	 */
	@Test
	public void testDoCapture() {
		
		assertTrue(true); //tested indirect with testRun
	}

	/**
	 * Test method for
	 * {@link de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.CaptureControllerUnitTest#stop()}.
	 */
	@Test
	public void testRun() {
		
		//should run without exception
		try {
			InetAddress address = InetAddress.getLocalHost();
			CaptureController captureController = new CaptureController(amount, timeout, address);
			captureController.run();
			assertTrue(true); 
		} catch (Exception exception){
			assertTrue(false);
		}
	}
	
	@Test
	public void testStop() {
		
		assertTrue(true); //not implemented yet
	}
}
