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

		TableColumn<PacketItem,Integer> index = new TableColumn<PacketItem,Integer>("Index");
		TableColumn<PacketItem,String> value = new TableColumn<PacketItem,String>("Packet");

		view.getColumns().add(index);
		view.getColumns().add(value);

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
		value.setCellValueFactory(
			    new PropertyValueFactory<PacketItem,String>("preview")
			);
		view.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		view.setItems(data);
		this.setPrefWidth(400);
		this.view.setPrefHeight(10000);
	}
}
