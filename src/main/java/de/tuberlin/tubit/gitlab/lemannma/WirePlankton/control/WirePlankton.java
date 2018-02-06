/**
 *
 */
package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import java.util.ArrayList;
import java.util.List;

import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;

//import prise.nexus.test.NexusConnection;

/**
 *
 * @author Anton, Matthias, Stefan, Lana
 *
 */
public class WirePlankton {

	public static final int SINGLECHOICE = 0;
	public static final int MULTICHOICE = 1;
	public static final int NUMBER = 2;

	public static void main(String[] args) {
		List<PcapNetworkInterface> interfaces = new ArrayList<PcapNetworkInterface>();
		try {
			interfaces = Pcaps.findAllDevs();
		} catch (PcapNativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<String> interfaceNames = new ArrayList<String>();
		for (PcapNetworkInterface nif : interfaces) {
			interfaceNames.add(nif.getName());
		}

		//SystemSettings
		String[] nifChoice = interfaceNames.toArray(new String[0]);
		String[] nifActive = nifChoice;
		String nifName = "Interface:";
		Setting nifSetting = new Setting("NIF", nifName, nifActive, SINGLECHOICE, nifChoice);
		MainController.addSetting(nifSetting);

		String[] amountActive = { "17" };
		String amountName = "Packets to be captured:";
		Setting amountSetting = new Setting("AMOUNT", amountName, amountActive, NUMBER);
		MainController.addSetting(amountSetting);

		String[] limitActive = { "1000"};
		String limitName = "Max. Size in KBytes:";
		Setting limitSetting = new Setting("LIMIT", limitName, limitActive, NUMBER);
		MainController.addSetting(limitSetting);

		String[] timeoutActive = { "60000"};
		String timeoutName = "Timeout in ms:";
		Setting timeoutSetting = new Setting("TIMEOUT", timeoutName, timeoutActive, NUMBER);
		MainController.addSetting(timeoutSetting);

		String[] ipChoice = {"ip","ip6"};
		String[] ipActive = {  };
		String ipName = "Packettypes";
		Setting ipSetting = new Setting("IPVERSION", ipName, ipActive, MULTICHOICE, ipChoice);
		MainController.addSetting(ipSetting);

		//ExportSettings
		String[] eipChoice = {"ip","ip6"};
		String[] eipActive = {  };
		String eipName = "Packettypes";
		Setting eipSetting = new Setting("E_IPVERSION", eipName, eipActive, MULTICHOICE, eipChoice);
		MainController.addExportSetting(eipSetting);

		String[] eamountActive = { "17" };
		String eamountName = "Packets to be captured:";
		Setting eamountSetting = new Setting("E_AMOUNT", eamountName, eamountActive, NUMBER);
		MainController.addExportSetting(eamountSetting);

		//DisplaySettings
		String[] dipChoice = {"all", "ipv4","ipv6"};
		String[] dipActive = { "all" };
		String dipName = "Packettypes";
		Setting dipSetting = new Setting("D_IPVERSION", dipName, dipActive, SINGLECHOICE, dipChoice);
		MainController.addDisplaySetting(dipSetting);

		//StatSettings
		String[] statChoice = {"Top 5 IP","Top 5 MACs"};
		String[] statActive = {statChoice[0]};
		String statName = "Statistic:";
		Setting statSetting = new Setting("STAT", statName, statActive, SINGLECHOICE, statChoice);
		MainController.addStatSetting(statSetting);

		ViewController.go();
	}
}