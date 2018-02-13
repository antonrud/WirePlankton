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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

public class Top5MACChart extends VBox implements Refreshable{

	private BarChart<String,Number> chart;

	@Override
	public void refresh() {

		Map<String, Integer> top5 = MainController.getTopMAC();

		XYChart.Series<String,Number> series1 = new XYChart.Series<String,Number>();
		for(Entry<String, Integer> e : top5.entrySet()){
			series1.getData().add(new XYChart.Data<String,Number>(e.getKey(),e.getValue()));
		}

		this.chart.getData().clear();
		this.chart.getData().add(series1);

		this.chart.setPrefHeight(9999);
		this.chart.setPrefWidth(9999);

	}

	public Top5MACChart(){
		super();

		final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        this.chart =
            new BarChart<String,Number>(xAxis,yAxis);
        chart.setTitle("Top 5 MACs");
        xAxis.setLabel("MAC");
        yAxis.setLabel("Amount");
        refresh();
        this.getChildren().add(this.chart);

	}
}
