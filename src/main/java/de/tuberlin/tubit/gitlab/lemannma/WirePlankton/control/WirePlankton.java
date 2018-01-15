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

		//Single Choice Setting
		String[] scChoice = {"this", "is", "a", "test"};
		String[] scActive = {"a"};
		String scField = "SINGLECHOICE";
		String scName = "sc Testcase";
		Setting scTest = new Setting(scName, scActive, scField, scChoice);
		MainController.addSetting(scTest);

		//Multi Choice
		String[] mcChoice = {"this", "is", "a", "test"};
		String[] mcActive = {"a"};
		String mcField = "MULTICHOICE";
		String mcName = "mc Testcase";
		Setting mcTest = new Setting(mcName, mcActive, mcField, mcChoice);
		MainController.addSetting(mcTest);

		//Text - Mal gucken, ob wir das brauchen

		//Number - Input für Nummern
		String[] nActive = {"17"};
		String nField = "NUMBER";
		String nName = "n Testcase";
		Setting nTest = new Setting(nName, nActive, nField);
		MainController.addSetting(nTest);

		//TODO Init Settings here!


		ViewController.go();
	}
}