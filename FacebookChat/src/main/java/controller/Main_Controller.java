package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.jivesoftware.smack.RosterEntry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class Main_Controller implements Initializable{
	public static ChatDialog testDialog;
	@FXML ListView<RosterEntry> listView;
	ObservableList<RosterEntry> listViewItem=FXCollections.observableArrayList();
	
	public void setList(List<RosterEntry> listViewItem){
		this.listViewItem.addAll(listViewItem);
		listView.setItems(this.listViewItem);
	}
	public void initialize(URL arg0, ResourceBundle arg1) {
		listView.setItems(listViewItem);
		listView.setCellFactory(new Callback<ListView<RosterEntry>, ListCell<RosterEntry>>() {

			public ListCell<RosterEntry> call(ListView<RosterEntry> arg0) {
				ListCell<RosterEntry> cell=new ListCell<RosterEntry>(){

					@Override
					protected void updateItem(RosterEntry user, boolean empty) {
						// TODO Auto-generated method stub
						super.updateItem(user, empty);
						if (empty) {
				            setText(null);
				            setGraphic(null);
						}else{
							GridPane grid=new GridPane();
							grid.setHgap(10);
							grid.setVgap(4);
							grid.setPadding(new Insets(0, 10, 0, 10));
							Label name=new Label(user.getName());
//				            name.getStyleClass().add("cache-list-name");
							Label username=new Label(user.getUser());
							grid.add(name, 0, 0);
							grid.add(username, 0, 1);
//							Label icon = new Label(GeocachingIcons.getIcon(cache).toString());
//				            icon.setFont(Font.font("FontAwesome", FontWeight.BOLD, 24));
//				            icon.getStyleClass().add("cache-list-icon");
//				            grid.add(icon, 0, 0, 1, 2);     
							setGraphic(grid);
						}
					}
					
				};
				return cell;
			}
		});
		listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent click) {
				// TODO Auto-generated method stub
				if (click.getClickCount()==2) {
					RosterEntry entry=listView.getSelectionModel().getSelectedItem();
					testDialog = new ChatDialog(entry);
					testDialog.showAndWait();
				}
			}
		});
	}

}
