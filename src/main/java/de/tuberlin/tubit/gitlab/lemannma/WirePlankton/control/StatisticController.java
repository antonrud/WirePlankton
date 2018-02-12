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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.IpV6Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.pcap4j.packet.UdpPacket;

// TODO: Auto-generated Javadoc
/**
 * The Class StatisticController.
 */
public class StatisticController {

	/** The mac list. */
	static Map<String, Integer> macList = new HashMap<String, Integer>();

	/** The ip 4 list. */
	static Map<String, Integer> ip4List = new HashMap<String, Integer>();

	/** The ip 6 list. */
	static Map<String, Integer> ip6List = new HashMap<String, Integer>();

	/** The ip 4 amount. */
	static int ip4Amount;

	/** The ip 6 amount. */
	static int ip6Amount;

	/** The tcp amount. */
	static int tcpAmount;

	/** The udp amount. */
	static int udpAmount;

	/**
	 * Instantiates a new statistic controller.
	 */
	public StatisticController() {

	}

	/**
	 * Evaluate packet.
	 *
	 * @param packet
	 *            the packet
	 */
	public static void evaluatePacket(Packet packet) {

		// Filling macList
		if (macList.containsKey(packet.get(EthernetPacket.class).getHeader().getSrcAddr().toString())) {
			macList.put(packet.get(EthernetPacket.class).getHeader().getSrcAddr().toString(),
					macList.get(packet.get(EthernetPacket.class).getHeader().getSrcAddr().toString()) + 1);
		} else {
			macList.put(packet.get(EthernetPacket.class).getHeader().getSrcAddr().toString(), 1);
		}

		// Counting ip version and filling ip lists
		if (packet.get(IpV4Packet.class) == null) {
			if (!(packet.get(IpV6Packet.class) == null)) {
				ip6Amount++;

				if (ip6List.containsKey(packet.get(IpV6Packet.class).getHeader().getSrcAddr().toString())) {
					ip6List.put(packet.get(IpV6Packet.class).getHeader().getSrcAddr().toString(),
							ip6List.get(packet.get(IpV6Packet.class).getHeader().getSrcAddr().toString()) + 1);
				} else {
					ip6List.put(packet.get(IpV6Packet.class).getHeader().getSrcAddr().toString(), 1);
				}
			}
		} else {
			ip4Amount++;

			if (ip4List.containsKey(packet.get(IpV4Packet.class).getHeader().getSrcAddr().toString())) {
				ip4List.put(packet.get(IpV4Packet.class).getHeader().getSrcAddr().toString(),
						ip4List.get(packet.get(IpV4Packet.class).getHeader().getSrcAddr().toString()) + 1);
			} else {
				ip4List.put(packet.get(IpV4Packet.class).getHeader().getSrcAddr().toString(), 1);
			}
		}

		// Counting packet type
		if (packet.get(TcpPacket.class) == null) {
			if (!(packet.get(UdpPacket.class) == null)) {
				udpAmount++;
			}
		} else {
			tcpAmount++;
		}
	}

	/**
	 * Gets the top IP 4.
	 *
	 * @return the top IP 4
	 */
	public static Map<String, Integer> getTopIP4() {

		// TODO This should be replaced to handle IndexOfBoundException
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("0.0.0.0", 50);
		map.put("0.0.0.1", 10);
		map.put("0.0.0.2", 3);
		map.put("0.0.0.3", 2);
		map.put("0.0.0.4", 1);
		return map;

		// return ip4List.entrySet().stream().sorted(Map.Entry.<String,
		// Integer>comparingByValue().reversed()).limit(5)
		// .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (hashMap,
		// linkedHashMap) -> hashMap,
		// LinkedHashMap::new));

	}

	/**
	 * Gets the top IP 6.
	 *
	 * @return the top IP 6
	 */
	public static Map<String, Integer> getTopIP6() {

		return ip6List.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(5)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (hashMap, linkedHashMap) -> hashMap,
						LinkedHashMap::new));
	}

	/**
	 * Gets the top MAC.
	 *
	 * @return the top MAC
	 */
	public static Map<String, Integer> getTopMAC() {

		return macList.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(5)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (hashMap, linkedHashMap) -> hashMap,
						LinkedHashMap::new));
	}

	/**
	 * Gets the percentage IP.
	 *
	 * @param version
	 *            the version
	 * @return the percentage IP
	 */
	public static float getPercentageIP(String version) {

		switch (version) {
		case "ip4":
			return (float) ip4Amount / getPacketAmount();

		case "ip6":
			return (float) ip6Amount / getPacketAmount();

		case "other":
			return 1 - (float) ((ip4Amount + ip6Amount) / getPacketAmount());

		default:
			System.out.println("[WARNING] Wrong IP version!");
			return 0;
		}
	}

	/**
	 * Gets the percentage type.
	 *
	 * @param type
	 *            the type
	 * @return the percentage type
	 */
	public static float getPercentageType(String type) {

		switch (type) {
		case "tcp":
			return (float) tcpAmount / getPacketAmount();

		case "udp":
			return (float) udpAmount / getPacketAmount();

		case "other":
			return 1 - (float) ((tcpAmount + udpAmount) / getPacketAmount());

		default:
			System.out.println("[WARNING] Wrong packet type!");
			return 0;
		}
	}

	/**
	 * Reset.
	 */
	public static void reset() {

		macList.clear();
		ip4List.clear();
		ip6List.clear();
		ip4Amount = 0;
		ip6Amount = 0;
		tcpAmount = 0;
		udpAmount = 0;
	}

	/**
	 * Gets the packet amount.
	 *
	 * @return the packet amount
	 */
	public static int getPacketAmount() {

		return MainController.getPacketList().size();
	}

	/**
	 * Checks if is implemented.
	 *
	 * @return true, if is implemented
	 */
	public boolean isImplemented() {

		return true;
	}
}
