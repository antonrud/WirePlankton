package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;

import java.util.LinkedList;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view.charts.Refreshable;
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
		stats.add(new Top5MACChart());
		changeStat(0);
	}

	public void changeStat(int stat){
		this.getChildren().clear();
		this.getChildren().add((Node) stats.get(stat));
	}

}
