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

		// Single Choice Setting
		// String[] scChoice = {"this", "is", "a", "test"};
		// String[] scActive = { "a" };
		String[] scChoice = interfaceNames.toArray(new String[0]);
		String[] scActive = scChoice;
		String scField = "SINGLECHOICE";
		String scName = "Interface:";
		Setting scTest = new Setting(scName, scActive, scField, scChoice);
		MainController.addSetting(scTest);

		// Multi Choice
		String[] mcChoice = { "this", "is", "a", "test","but","I","need","MOOOORE","Öptions","","Wuuuut","usw","\nTroll" };
		String[] mcActive = { "a" };
		String mcField = "MULTICHOICE";
		String mcName = "mc Testcase";
		Setting mcTest = new Setting(mcName, mcActive, mcField, mcChoice);
		MainController.addSetting(mcTest);

		// Text - Mal gucken, ob wir das brauchen

		// Number - Input fï¿½r Nummern
		String[] nActive = { "17" };
		String nField = "NUMBER";
		String nName = "n Testcase";
		Setting nTest = new Setting(nName, nActive, nField);
		MainController.addSetting(nTest);

		// Number - Input fï¿½r Nummern
		String[] neActive = { "17" };
		String neField = "NUMBER";
		String neName = "n Testcase";
		Setting neTest = new Setting(nName, nActive, nField);
		MainController.addExportSetting(neTest);

		ViewController.go();
	}
}