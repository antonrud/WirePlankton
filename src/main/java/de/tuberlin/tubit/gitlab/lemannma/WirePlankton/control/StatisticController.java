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

/**
 * This class provides fields and methods for statistics purposes.
 * 
 * @author Anton Rudacov
 * 
 */
public class StatisticController {

	/** Map for MACs and their particle amount. */
	static Map<String, Integer> macList = new HashMap<String, Integer>();

	/** Map for IPv4s and their particle amount. */
	static Map<String, Integer> ip4List = new HashMap<String, Integer>();

	/** Map for IPv6s and their particle amount. */
	static Map<String, Integer> ip6List = new HashMap<String, Integer>();

	/** Amount of captured packets with IP4 header. */
	static int ip4Amount;

	/** Amount of captured packets with IP6 header. */
	static int ip6Amount;

	/** Amount of captured packets with TCP header. */
	static int tcpAmount;

	/** Amount of captured packets with IUDP header. */
	static int udpAmount;

	/**
	 * StatisticController only uses static methods.
	 */
	private StatisticController() {

	}

	/**
	 * This method evaluates captured packets.
	 *
	 * @param packet
	 *            the captured packet
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
	 * Gets the top 5 IPv4 packets.
	 *
	 * @return the top5 IPv4 packets
	 */
	public static Map<String, Integer> getTopIP4() {

		return ip4List.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(5)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (hashMap, linkedHashMap) -> hashMap,
						LinkedHashMap::new));

	}

	/**
	 * Gets the top 5 IPv6 packets.
	 *
	 * @return the top5 IPv6 packets
	 */
	public static Map<String, Integer> getTopIP6() {

		return ip6List.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(5)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (hashMap, linkedHashMap) -> hashMap,
						LinkedHashMap::new));
	}

	/**
	 * Gets the top 5 MACs ofpackets.
	 *
	 * @return the top5 MACs of packets
	 */
	public static Map<String, Integer> getTopMAC() {

		return macList.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(5)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (hashMap, linkedHashMap) -> hashMap,
						LinkedHashMap::new));
	}

	/**
	 * Gets the percentage of packets by ip version.
	 *
	 * @param version
	 *            the ip version of packet
	 * @return the percentage of packets by ip version
	 */
	public static float getPercentageIP(String version) {
		if (MainController.getPacketList().size() == 0) {
			return 0;
		}
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
	 * Gets the percentage of packets by packet type.
	 *
	 * @param type
	 *            the type of packet
	 * @return the percentage of packets by type
	 */
	public static float getPercentageType(String type) {
		if (MainController.getPacketList().size() == 0) {
			return 0;
		}
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
	 * Reset statistics fields.
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
	 * Gets the overall packet amount.
	 *
	 * @return the overall packet amount
	 */
	public static int getPacketAmount() {

		return MainController.getPacketList().size();
	}
}