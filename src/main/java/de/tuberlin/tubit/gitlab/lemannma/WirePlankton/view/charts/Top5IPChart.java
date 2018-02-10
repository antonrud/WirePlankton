package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.charts;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

public class Top5IPChart extends VBox implements Refreshable{

	private BarChart<String,Number> chart;
	private LinkedList<String> ips;
	private LinkedList<Number> values;
	@Override
	public void refresh() {
		//Number[] dummyA = {200,180,160,140,120};
		//String[] dummyI = {"192.168.0.1","192.168.0.2","192.168.0.3","192.168.0.4","192.168.0.5"};
		
		String[] dummyI = MainController.getTopIPs().keySet().toArray(new String[0]);
		Number[] dummyA = MainController.getTopIPs().values().toArray(new Number[0]);
		
		ips.addAll(Arrays.asList(dummyI));
		values.addAll(Arrays.asList(dummyA));

		XYChart.Series<String,Number> series1 = new XYChart.Series<String,Number>();
		for(int i=0;i<5;i++){
			series1.getData().add(new XYChart.Data<String,Number>(ips.get(i),values.get(i)));
		}
		this.chart.getData().clear();
		this.chart.getData().add(series1);

		this.chart.setPrefHeight(9999);
		this.chart.setPrefWidth(9999);

	}

	public Top5IPChart(){
		super();
		this.ips = new LinkedList<String>();
		this.values = new LinkedList<Number>();
		final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        this.chart =
            new BarChart<String,Number>(xAxis,yAxis);
        chart.setTitle("Top 5 IPs");
        xAxis.setLabel("IP");
        yAxis.setLabel("Amount");
        refresh();
        this.getChildren().add(this.chart);

	}
}
