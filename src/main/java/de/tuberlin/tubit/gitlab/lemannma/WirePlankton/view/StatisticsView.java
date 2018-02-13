/*
 * Copyright (c) 2017-2018 Anton Rudacov, Stefan Pawlowski, Matthias Lehmann, Svetlana Lepikhine
 *
 * WirePlankton
 * A small network traffic analyzer.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;

import java.util.LinkedList;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.charts.Refreshable;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.charts.Top5IPChart;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.charts.Top5MACChart;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;


/**
 * The Class StatisticsView.
 *
 * @author Stefan
 */

public class StatisticsView extends VBox{

	private LinkedList<Refreshable> stats;

	public StatisticsView(){
		super();
		stats = new LinkedList<Refreshable>();
		stats.add(new Top5IPChart());
		stats.add(new Top5MACChart());
		changeStat(0);
	}


	/**
	 * Change statistics by getting the children and adding a node.
	 *
	 *
	 */

	public void changeStat(int stat){
		this.getChildren().clear();
		this.getChildren().add((Node) stats.get(stat));
	}

}
