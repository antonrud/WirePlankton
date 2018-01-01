package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;


import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.ViewController;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.PacketViewItem;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class PacketView extends VBox{

	ObservableList<PacketViewItem> data;
	TableView<PacketViewItem> view;

	public PacketView(){

		data = MainController.getPacketList();
		view = new TableView<PacketViewItem>();
		this.getChildren().add(view);

		TableColumn<PacketViewItem,Integer> index = new TableColumn<PacketViewItem,Integer>("Index");
		TableColumn<PacketViewItem,String> value = new TableColumn<PacketViewItem,String>("Packet");

		view.getColumns().add(index);
		view.getColumns().add(value);

		view.setRowFactory(tableView -> {
		    TableRow<PacketViewItem> tableRow = new TableRow<>();
		    tableRow.setOnMouseClicked(event -> {
		        if (!tableRow.isEmpty()) {
		        	PacketViewItem rowContent = tableRow.getItem();
		            ViewController.getRealtimeview().setPacket(rowContent.getP());
		        }
		    });
		    return tableRow ;
		});

		index.setCellValueFactory(
			    new PropertyValueFactory<PacketViewItem,Integer>("index")
			);
		value.setCellValueFactory(
			    new PropertyValueFactory<PacketViewItem,String>("preview")
			);
		view.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		view.setItems(data);
		this.setPrefWidth(400);
		this.view.setPrefHeight(10000);
	}
}
