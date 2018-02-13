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

public class IP46Chart extends VBox implements Refreshable{

	final PieChart chart;
	final ObservableList<PieChart.Data> data;

	public IP46Chart(){
		data = FXCollections.observableArrayList();
		chart = new PieChart(data);
		chart.setTitle("IPv4/IPv6");
		chart.setLegendVisible(true);
		refresh();

		 this.getChildren().add(this.chart);

	        this.chart.setPrefHeight(9999);
			this.chart.setPrefWidth(9999);


	}

	@Override
	public void refresh() {
		float ip4 = MainController.getPercentageIP("ip4");
		float ip6 = MainController.getPercentageIP("ip6");
		float other = MainController.getPercentageIP("other");
		if(ip4 == 0 && ip6 == 0 && other == 0){
			return;
		}
		data.clear();
		data.add(new PieChart.Data("IPv4", ip4));
		data.add(new PieChart.Data("IPv6", ip6));
		data.add(new PieChart.Data("Other", other));


	}

}
