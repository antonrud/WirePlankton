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
public class CaptureController implements Runnable {

	private static final PromiscuousMode PROMISCOUS_MODE = PromiscuousMode.PROMISCUOUS;
	private static final int SNAP_LEN = 65536;

	int amount;
	int timeout;
	InetAddress address;

	public CaptureController(int amount, int timeout, InetAddress address) {

		this.amount = amount;
		this.timeout = timeout;
		this.address = address;
	}

	public void doCapture(int amount, int timeout, InetAddress address) throws Exception {
		PcapNetworkInterface netInterface = Pcaps.getDevByAddress(address);
		PcapHandle handle = netInterface.openLive(SNAP_LEN, PROMISCOUS_MODE, timeout);

		int packetNr = 1;

		while (packetNr <= amount) {
			MainController.addPacket(handle.getNextPacketEx());
			packetNr++;
		}

		handle.close();
	}

	@Override
	public void run() {
		try {
			doCapture(amount, timeout, address);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public void stop() {
	// // TODO A good approach might be to run doCapture as own Thread.
	// // In order to stop() - interrupt capture thread and close handle
	//
	// }
}
