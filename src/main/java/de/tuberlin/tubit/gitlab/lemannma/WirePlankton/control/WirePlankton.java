/**
 *
 */
package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import java.util.ArrayList;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.Setting;

//import prise.nexus.test.NexusConnection;

/**
 *
 * @author Anton, Matthias, Stefan, Lana
 *
 */
public class WirePlankton {
	public static void main(String[] args) {
		//NexusConnection.textNexusConnection();
		ArrayList<String> testlist = new ArrayList<String>();
		ArrayList<String> active = new ArrayList<String>();
		active.add("a");
		testlist.add("this");
		testlist.add("is");
		testlist.add("a");
		testlist.add("test");

		//TODO Init Settings here!
		Setting testChoice = new Setting("TESTCASE", active, "SINGLECHOICE", testlist);
		System.out.println(testlist.get(3));
		MainController.addSetting(testChoice);

		ViewController.go();
	}
}