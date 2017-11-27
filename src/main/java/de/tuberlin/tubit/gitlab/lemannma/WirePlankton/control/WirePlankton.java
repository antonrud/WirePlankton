/**
 *
 */
package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

/**
 *
 * @author Anton, Matthias, Stefan, Lana
 *
 */
public class WirePlankton {
	public static void main(String[] args) {

		try {
			MainController.setAddress("localhost");
			MainController.capturePacket(2, 30);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ViewController.go();
	}
}
