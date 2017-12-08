package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import java.net.InetAddress;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;

/**
 * @author Anton Rudacov
 *
 */
public class CaptureController {

	private static final PromiscuousMode PROMISCOUS_MODE = PromiscuousMode.PROMISCUOUS;
	private static final int SNAP_LEN = 65536;
	private static boolean continueCapture;
	
	public static void doCapture(int amount, int timeout, InetAddress address) throws Exception {
		
		PcapNetworkInterface netInterface = Pcaps.getDevByAddress(address);
		PcapHandle handle = netInterface.openLive(SNAP_LEN, PROMISCOUS_MODE, timeout);

		continueCapture = true;
		int packetNr = 1;
		//make this to while and 
		while (packetNr <= amount && continueCapture) {
			MainController.addPacket(handle.getNextPacketEx());
			packetNr++;
		}
		
		handle.close();
	}

	public static void stop() {
		// TODO A good approach might be to run doCapture as own Thread. 
		// In order to stop() - interrupt capture thread and close handle 
		continueCapture = false;
	}

}
