package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import java.net.InetAddress;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;

/**
 * @author Anton Rudacov
 *
 */
public class CaptureController implements Runnable {

	private static final PromiscuousMode PROMISCOUS_MODE = PromiscuousMode.PROMISCUOUS;
	private static final int SNAP_LEN = 65536;

	int amount;
	int limit;
	int timeout;
	InetAddress address;

	public CaptureController(int amount, int limit, int timeout, InetAddress address) {

		this.amount = amount;
		this.limit = limit;
		this.timeout = timeout;
		this.address = address;
	}

	public void doCapture(int amount, int limit, int timeout, InetAddress address) throws Exception {
		PcapNetworkInterface netInterface = Pcaps.getDevByAddress(address);
		PcapHandle handle = netInterface.openLive(SNAP_LEN, PROMISCOUS_MODE, timeout);

		if (amount <= 0) {

			int captured = 0;
			Packet packet;

			while (captured < limit) {
				packet = handle.getNextPacketEx();
				MainController.addPacket(packet);
				captured = packet.length();
			}
		} else {

			int packetNr = 1;

			while (packetNr <= amount) {
				MainController.addPacket(handle.getNextPacketEx());
				packetNr++;
			}
		}

		handle.close();
	}

	@Override
	public void run() {
		try {
			doCapture(amount, limit, timeout, address);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
