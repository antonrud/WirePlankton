package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.charts;

import java.io.IOException;
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


/**
 * The Class Top5IP6Chart.
 *
 * @author Stefan
 */


public class Top5IP6Chart extends VBox implements Refreshable{

	private BarChart<String,Number> chart;
	private LinkedList<String> ips;
	private LinkedList<Number> values;



	/**
	 * Refresh in order to adjust the statistic.
	 *
	 */

	@Override
	public void refresh() {
		//Number[] dummyA = {200,180,160,140,120};
		//String[] dummyI = {"192.168.0.1","192.168.0.2","192.168.0.3","192.168.0.4","192.168.0.5"};

		Map<String, Integer> top5 = MainController.getTopIP6();

		XYChart.Series<String,Number> series1 = new XYChart.Series<String,Number>();
		for(Entry<String, Integer> e : top5.entrySet()){
			series1.getData().add(new XYChart.Data<String,Number>(e.getKey(),e.getValue()));
		}
		this.chart.getData().clear();
		this.chart.getData().add(series1);

	}

	public Top5IP6Chart(){
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

        this.chart.setPrefHeight(9999);
		this.chart.setPrefWidth(9999);

	}
}
