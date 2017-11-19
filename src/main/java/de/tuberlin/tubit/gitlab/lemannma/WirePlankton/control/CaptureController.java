package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import java.util.ArrayList;

import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.packet.Packet;

/**
 * @author Anton Rudacov
 *
 */
public class CaptureController {

	static final int SNAP_LEN = 65536;
	static final PromiscuousMode MODE = PromiscuousMode.PROMISCUOUS;

	static public ArrayList<Packet> doCapture(PcapNetworkInterface netInterface, int amount, int timeout, ArrayList<Packet> packetList) throws Exception {

		PcapHandle pcapHandle = netInterface.openLive(SNAP_LEN, MODE, timeout);

		for (int packetNr = 1; packetNr <= amount; packetNr++) {
			packetList.add(pcapHandle.getNextPacketEx());
		}

		pcapHandle.close();
		System.out.println("Capture done");

		return packetList;
	}

	public static void stop() {
		// TODO Auto-generated method stub

	}

}
