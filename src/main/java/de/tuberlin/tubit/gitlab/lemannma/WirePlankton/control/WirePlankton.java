/**
 *
 */
package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import java.net.InetAddress;

/**
 *
 * @author Anton, Matthias, Stefan, Lana
 *
 */
public class WirePlankton {
	public static void main(String[] args) {

		try {
			InetAddress address = InetAddress.getLocalHost();
			MainController.capturePacket(3, 10000, address);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ViewController.go();
	}
}