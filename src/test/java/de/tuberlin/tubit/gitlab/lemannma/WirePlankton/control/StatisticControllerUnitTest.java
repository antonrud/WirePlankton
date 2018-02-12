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

import static org.junit.Assert.*;

import org.junit.Test;

public class StatisticControllerUnitTest {

	@Test
	public void isImpementedTest() {
		
		StatisticController statisticController = new StatisticController();
	
		assertTrue(statisticController.isImplemented()); //not implemented et
	}

}
