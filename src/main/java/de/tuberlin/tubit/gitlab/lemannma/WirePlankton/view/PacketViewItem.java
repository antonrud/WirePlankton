package de.tuberlin.tubit.gitlab.lemannma.WirePlankton.view;

import org.pcap4j.packet.Packet;

import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.MainController;
import de.tuberlin.tubit.gitlab.lemannma.WirePlankton.control.ViewController;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class PacketViewItem extends HBox{

	Label index;
	Label preview;
	Packet p;

	public PacketViewItem(Packet p, int index){
		this.p = p;
		this.index = new Label(""+index);
		this.preview = new Label(p.toString());

		this.getChildren().add(this.index);
		this.getChildren().add(this.preview);

		this.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Packet p = ((PacketViewItem) event.getSource()).getP();
				System.out.print(p.toString());
				ViewController.getRealtimeview().setPacket(p);

			}

		});

	}

	public Label getIndex() {
		return index;
	}

	public Label getPreview() {
		return preview;
	}

	public Packet getP() {
		return p;
	}



}
