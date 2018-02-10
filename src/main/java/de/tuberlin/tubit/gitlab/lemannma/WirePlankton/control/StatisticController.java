package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import java.util.Map;

import org.pcap4j.packet.Packet;

public class StatisticController {

	static Map<String, Number> topIPs;
	static Map<String, Number> topMACs;

	private StatisticController() {

	}

	public static void evaluatePacket(Packet packet) {

	}

	public static Map<String, Number> getTopIPs() {

		return topIPs;
	}

	public static Map<String, Number> getTopMACs() {

		return topMACs;
	}

	public static int getPercentageByType(String type) {

		switch (type) {
		case "ip6":
			break;
		}

		return 0;
	}

	public static void reset() {

	}
}
