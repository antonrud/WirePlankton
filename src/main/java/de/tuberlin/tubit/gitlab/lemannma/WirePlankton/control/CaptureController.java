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

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.PacketItem;

import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;

/**
 * @author Anton Rudacov
 *
 */
public class CaptureController implements Runnable {

	private static final PromiscuousMode PROMISCOUS_MODE = PromiscuousMode.PROMISCUOUS;
	private static final int SNAP_LEN = 65536;
	PcapHandle handle;

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

	public void doCapture() throws TimeoutException, EOFException, PcapNativeException, NotOpenException {
		MainController.clearPacketList();
		PacketItem.resetIndexGen();

		handle = nif.openLive(SNAP_LEN, PROMISCOUS_MODE, timeout);

		System.out.println(filter);

		if (filter != null && filter.length() != 0) {
			handle.setFilter(filter, BpfCompileMode.OPTIMIZE);
		}

		if (amount <= 0) {

			int captured = 0;
			Packet packet;

			while ((captured += (packet = handle.getNextPacketEx()).length()) < limit) {
				MainController.addPacket(packet);
			}
		} else {

			int captured = 0;
			Packet packet;

			for (int packetNr = 1; packetNr <= amount; packetNr++) {
				if ((captured += (packet = handle.getNextPacketEx()).length()) < limit) {
					MainController.addPacket(packet);
				} else {
					packetNr = amount;
				}
			}
		}

		handle.close();
	}

	public void closeHandel() {
		handle.close();
	}

	@Override
	public void run() {

		try {
			doCapture();
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
