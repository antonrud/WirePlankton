package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control;

import static org.junit.Assert.*;

import org.junit.Test;

public class StatisticControllerUnitTest {

	@Test
	public void isImpementedTest() {
		
		StatisticController statisticController = new StatisticController();
	
		assertTrue(!statisticController.isImplemented()); //not implemented et
	}

}
