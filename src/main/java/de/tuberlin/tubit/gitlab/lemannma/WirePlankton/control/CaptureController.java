package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import java.io.EOFException;
import java.util.concurrent.TimeoutException;

import org.pcap4j.core.BpfProgram.BpfCompileMode;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
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
	PcapNetworkInterface nif;
	String filter;

	public CaptureController(int amount, int limit, int timeout, String interfaceName, String filter)
			throws PcapNativeException {

		this.amount = amount;
		this.limit = limit;
		this.timeout = timeout;
		this.nif = Pcaps.getDevByName(interfaceName);
		this.filter = filter;
	}

	public void doCapture(int amount, int limit, int timeout, PcapNetworkInterface nif, String filter)
			throws TimeoutException, EOFException, PcapNativeException, NotOpenException {

		PcapHandle handle = nif.openLive(SNAP_LEN, PROMISCOUS_MODE, timeout);

		if (filter != "") {
			handle.setFilter(filter, BpfCompileMode.OPTIMIZE);
		}

		if (amount <= 0) {

			int captured = 0;
			Packet packet;

			while ((captured += (packet = handle.getNextPacketEx()).length()) < limit) {
				MainController.addPacket(packet);
			}
		} else {

			for (int packetNr = 1; packetNr <= amount; packetNr++) {
				MainController.addPacket(handle.getNextPacketEx());
			}
		}

		handle.close();
	}

	@Override
	public void run() {

		try {
			doCapture(amount, limit, timeout, nif, filter);
		} catch (TimeoutException e) {
			MainController.stopCapture();

		} catch (NotOpenException e) {
			MainController.stopCapture();

		} catch (EOFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (PcapNativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
