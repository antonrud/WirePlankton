/*
 * Copyright (c) 2017-2018 Anton Rudacov, Stefan Pawlowski, Matthias Lehmann, Svetlana Lepikhine
 *
 * WirePlankton
 * A small network traffic analyzer.
 * 
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import java.io.EOFException;
import java.util.concurrent.TimeoutException;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.packet.Packet;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.PacketItem;

// TODO: Auto-generated Javadoc
/**
 * The Class CaptureController.
 *
 * @author Anton Rudacov
 */
public class CaptureController implements Runnable {

	/** The handle. */
	PcapHandle handle;

	/** The amount. */
	int amount;

	/** The limit. */
	int limit;

	/**
	 * Instantiates a new capture controller.
	 *
	 * @param handle
	 *            the handle
	 * @param amount
	 *            the amount
	 * @param limit
	 *            the limit
	 */
	public CaptureController(PcapHandle handle, int amount, int limit) {

		this.handle = handle;
		this.amount = amount;
		this.limit = limit;
	}

	/**
	 * Do capture.
	 *
	 * @throws TimeoutException
	 *             the timeout exception
	 * @throws EOFException
	 *             the EOF exception
	 * @throws PcapNativeException
	 *             the pcap native exception
	 * @throws NotOpenException
	 *             the not open exception
	 */
	public void doCapture() throws TimeoutException, EOFException, PcapNativeException, NotOpenException {
		MainController.clearPacketList();
		MainController.resetStatistic();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		try {
			doCapture();
		} catch (TimeoutException e) {
			MainController.stopCapture();

		} catch (NotOpenException e) {
			System.out.println("[WARNING] Handle closed!");
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
