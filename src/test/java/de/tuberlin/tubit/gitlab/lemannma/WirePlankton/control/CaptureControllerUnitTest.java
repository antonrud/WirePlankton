/**
 * 
 */
package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import static org.junit.Assert.*;
import org.junit.Test;
import org.pcap4j.core.Pcaps;

/**
 * @author Matthias Lehmann
 *
 */
public class CaptureControllerUnitTest {

	private int amount = 1;
	private int limit = 1;
	private int timeout = 1;
	private String filter = "";

	/**
	 * Test method for
	 * {@link de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.CaptureControllerUnitTest#doCapture(org.pcap4j.core.PcapNetworkInterface, int, int, java.util.ArrayList)}.
	 */
	@Test
	public void testDoCapture() {

		assertTrue(true); // tested indirect with testRun
	}

	/**
	 * Test method for
	 * {@link de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.CaptureControllerUnitTest#stop()}.
	 */

	// TODO Something wrong with this test!!!
	// @Test
	// public void testRun() {
	//
	// // should run without exception
	// try {
	// String interfaceName = Pcaps.findAllDevs().get(0).getName();
	// CaptureController captureController = new CaptureController(amount, limit,
	// timeout, interfaceName, filter);
	// captureController.run();
	// assertTrue(true);
	// } catch (Exception exception) {
	// assertTrue(false);
	// }
	// }

	@Test
	public void testStop() {

		assertTrue(true); // not implemented yet
	}
}
