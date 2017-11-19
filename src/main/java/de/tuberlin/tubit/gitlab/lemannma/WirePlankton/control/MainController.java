package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import java.net.InetAddress;
import java.nio.file.Path;
import java.util.ArrayList;

import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;

/**
 * @author Anton Rudacov
 *
 */
public class MainController {

	static private ArrayList<Packet> packetList;
	static private InetAddress address;

	static public void capturePacket(int amount, int timeout) throws Exception {
		CaptureController.doCapture(Pcaps.getDevByAddress(address), amount, timeout, packetList);
	}

	static public void stopCapture() {
		CaptureController.stop();
	}

	static public void importData(Path path) {
		packetList = ImportExportController.doImport();
	}

	static public void exportData(Path path) {

	}

	static public void safe(Path path) {
		// TODO failure handling
	}

	static public void load() {

	}

	static public ArrayList<Setting> getSettings() {
		return SettingsController.getSettigsList();
	}

	static public Setting getSetting(String name) {
		return SettingsController.getSettig();
	}

	static public void setSetting(Setting setting) {

	}

	static public void initApp() {

	}

	static public void setAddress(String ip) throws Exception {
		address = InetAddress.getByName(ip);
	}

	static public String getAddress() {
		return address.toString();
	}

}
