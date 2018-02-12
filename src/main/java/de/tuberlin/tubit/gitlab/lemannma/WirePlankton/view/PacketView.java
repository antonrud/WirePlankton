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


import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.ViewController;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.PacketItem;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class PacketView extends VBox{

	ObservableList<PacketItem> data;
	TableView<PacketItem> view;

	public PacketView(){

		data = MainController.getPacketList();
		view = new TableView<PacketItem>();
		this.getChildren().add(view);

		TableColumn<PacketItem,Integer> index = new TableColumn<PacketItem,Integer>("Nr");
		TableColumn<PacketItem,String> cAt = new TableColumn<PacketItem,String>("Time");
		TableColumn<PacketItem,String> length = new TableColumn<PacketItem,String>("Length");
		TableColumn<PacketItem,String> dest = new TableColumn<PacketItem,String>("Destination");
		TableColumn<PacketItem,String> src = new TableColumn<PacketItem,String>("Source");
		TableColumn<PacketItem,String> type = new TableColumn<PacketItem,String>("Type");

		view.getColumns().add(index);
		view.getColumns().add(cAt);
		view.getColumns().add(length);
		view.getColumns().add(dest);
		view.getColumns().add(src);
		view.getColumns().add(type);



		view.setRowFactory(tableView -> {
		    TableRow<PacketItem> tableRow = new TableRow<>();
		    tableRow.setOnMouseClicked(event -> {
		        if (!tableRow.isEmpty()) {
		        	PacketItem rowContent = tableRow.getItem();
		            ViewController.getRealtimeview().setPacket(rowContent.getP());
		        }
		    });
		    return tableRow ;
		});

		index.setCellValueFactory(
			    new PropertyValueFactory<PacketItem,Integer>("index")
			);
		cAt.setCellValueFactory(
			    new PropertyValueFactory<PacketItem,String>("capturedAt")
			);
		length.setCellValueFactory(
			    new PropertyValueFactory<PacketItem,String>("originalLength")
			);
		dest.setCellValueFactory(
			    new PropertyValueFactory<PacketItem,String>("destinationAddress")
			);
		src.setCellValueFactory(
			    new PropertyValueFactory<PacketItem,String>("sourceAddress")
			);
		type.setCellValueFactory(
			    new PropertyValueFactory<PacketItem,String>("packetType")
			);
		view.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		view.setItems(data);
		this.setPrefWidth(400);
		this.view.setPrefHeight(10000);
	}

	public void reloadTable() {
		// TODO Auto-generated method stub

	}
}
