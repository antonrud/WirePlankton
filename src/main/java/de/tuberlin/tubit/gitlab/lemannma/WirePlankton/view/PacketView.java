package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;


import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.ViewController;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.model.PacketViewItem;
import javafx.collections.FXCollections;
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
		data = FXCollections.observableArrayList();
		view = new TableView<PacketViewItem>();
		this.setStyle("background-color: yellow;");
		this.getChildren().add(view);

		TableColumn<PacketViewItem,Integer> index = new TableColumn<PacketViewItem,Integer>("Index");
		TableColumn<PacketViewItem,String> value = new TableColumn<PacketViewItem,String>("Packet");

		view.getColumns().add(index);
		view.getColumns().add(value);

		view.setRowFactory(tv -> {
		    TableRow<PacketViewItem> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (!row.isEmpty()) {
		        	PacketViewItem rowContent = row.getItem();
		            ViewController.getRealtimeview().setPacket(rowContent.getP());
		        }
		    });
		    return row ;
		});

		index.setCellValueFactory(
			    new PropertyValueFactory<PacketViewItem,Integer>("index")
			);
		value.setCellValueFactory(
			    new PropertyValueFactory<PacketViewItem,String>("preview")
			);
		view.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		view.setItems(data);

		generatePacketList();
	}

	public void generatePacketList() {
		data.clear();
		//this.getChildren().clear();
		for(int iItem=0; iItem < MainController.getPacketList().size();iItem++){
			data.add(new PacketViewItem(MainController.getPacketList().get(iItem), iItem));
			//this.getChildren().add(new PacketViewItem(MainController.getPacketList().get(iItem), iItem));
		}
	}


}
