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

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.charts.ByPackageTypeChart;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.charts.IP46Chart;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.charts.Refreshable;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.charts.Top5IP6Chart;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.charts.Top5IPChart;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.charts.Top5MACChart;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class StatisticsView extends VBox{

	private LinkedList<Refreshable> stats;

	public StatisticsView(){
		super();
		stats = new LinkedList<Refreshable>();
		stats.add(new Top5IPChart());
		stats.add(new Top5IP6Chart());
		stats.add(new Top5MACChart());
		stats.add(new IP46Chart());
		stats.add(new ByPackageTypeChart());
		changeStat(0);
	}

	public void changeStat(int stat){
		if(stat== 0){
			this.getChildren().clear();
			return;
		}
		this.getChildren().clear();
		stats.get(stat-1).refresh();
		this.getChildren().add((Node) stats.get(stat-1));
	}

}
