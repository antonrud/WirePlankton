package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.charts;

import java.util.Arrays;
import java.util.LinkedList;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

public class Top5MACChart extends VBox implements Refreshable{

	private BarChart<String,Number> chart;
	private LinkedList<String> macs;
	private LinkedList<Number> values;
	@Override
	public void refresh() {
		Number[] dummyA = {200,120,110,80,5};
		String[] dummyI = {"00:00:00:00:00:01","00:00:00:00:00:02","00:00:00:00:00:03","00:00:00:00:00:04","00:00:00:00:00:05",};
		macs.addAll(Arrays.asList(dummyI));
		values.addAll(Arrays.asList(dummyA));

		XYChart.Series<String,Number> series1 = new XYChart.Series<String,Number>();
		for(int i=0;i<5;i++){
			series1.getData().add(new XYChart.Data<String,Number>(macs.get(i),values.get(i)));
		}
		this.chart.getData().clear();
		this.chart.getData().add(series1);

		this.chart.setPrefHeight(9999);
		this.chart.setPrefWidth(9999);

	}

	public Top5MACChart(){
		super();
		this.macs = new LinkedList<String>();
		this.values = new LinkedList<Number>();
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
