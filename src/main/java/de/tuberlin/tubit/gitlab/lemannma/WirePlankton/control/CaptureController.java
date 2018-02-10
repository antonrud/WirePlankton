package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import java.io.EOFException;
import java.util.concurrent.TimeoutException;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.packet.Packet;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.PacketItem;

/**
 * @author Anton Rudacov
 *
 */
public class CaptureController implements Runnable {

	// private static final PromiscuousMode PROMISCOUS_MODE =
	// PromiscuousMode.PROMISCUOUS;
	// private static final int SNAP_LEN = 65536;

	PcapHandle handle;
	int amount;
	int limit;

	public CaptureController(PcapHandle handle, int amount, int limit) {

		this.handle = handle;
		this.amount = amount;
		this.limit = limit;
	}

	public void doCapture() throws TimeoutException, EOFException, PcapNativeException, NotOpenException {
		MainController.clearPacketList();
		PacketItem.resetIndexGen();

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
