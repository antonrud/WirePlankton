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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

public class Top5IPChart extends VBox implements Refreshable{

	private BarChart<String,Number> chart;

	@Override
	public void refresh() {
		//Number[] dummyA = {200,180,160,140,120};
		//String[] dummyI = {"192.168.0.1","192.168.0.2","192.168.0.3","192.168.0.4","192.168.0.5"};


		Map<String, Integer> top5 = MainController.getTopIP4();

		XYChart.Series<String,Number> series1 = new XYChart.Series<String,Number>();
		for(Entry<String, Integer> e : top5.entrySet()){
			series1.getData().add(new XYChart.Data<String,Number>(e.getKey(),e.getValue()));
		}
		this.chart.getData().clear();
		this.chart.getData().add(series1);

	}

	public Top5IPChart(){
		super();
		final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        this.chart =
            new BarChart<String,Number>(xAxis,yAxis);
        chart.setTitle("Top 5 IPs");
        xAxis.setLabel("IP");
        yAxis.setLabel("Amount");
        refresh();

        this.getChildren().add(this.chart);

        this.chart.setPrefHeight(9999);
		this.chart.setPrefWidth(9999);

	}
}
