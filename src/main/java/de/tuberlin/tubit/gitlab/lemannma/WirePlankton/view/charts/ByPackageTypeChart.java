/*
 * Copyright (c) 2017-2018 Anton Rudacov, Stefan Pawlowski, Matthias Lehmann, Svetlana Lepikhine
 *
 * WirePlankton
 * A small network traffic analyzer.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.charts;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;

public class ByPackageTypeChart extends VBox implements Refreshable{

	final PieChart chart;
	final ObservableList<PieChart.Data> data;

	public ByPackageTypeChart(){
		data = FXCollections.observableArrayList();
		chart = new PieChart(data);
		chart.setTitle("TCP/UDP");
		chart.setLegendVisible(true);
		refresh();

		 this.getChildren().add(this.chart);

	        this.chart.setPrefHeight(9999);
			this.chart.setPrefWidth(9999);
	}

	@Override
	public void refresh() {
		float tcp = MainController.getPercentageType("tcp");
		float udp = MainController.getPercentageType("udp");
		float other = MainController.getPercentageType("other");
		if(tcp == 0 && udp == 0 && other == 0){
			return;
		}
		data.clear();
		data.add(new PieChart.Data("IPv4", tcp));
		data.add(new PieChart.Data("IPv6", udp));
		data.add(new PieChart.Data("Other", other));


	}

}