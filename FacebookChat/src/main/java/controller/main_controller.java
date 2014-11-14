package controller;

import java.net.URL;
import java.util.ResourceBundle;

import model.User;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class main_controller implements Initializable {
	@FXML ListView<String> listView;
	ObservableList<String> listViewItem=FXCollections.observableArrayList();
	public void initialize(URL arg0, ResourceBundle arg1) {
		listViewItem.add("asdsadsa");
		listViewItem.add("bbbbbb");
		listViewItem.add("asdsadsa");
		listViewItem.add("asdsadsa");
		listView.setItems(listViewItem);
		listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			
			public ListCell<String> call(ListView<String> arg0) {
				// TODO Auto-generated method stub
				User user=new User();
//				return user.getView();
				return null;
			}
		});
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> arg0,
					String arg1, String arg2) {
				
			}
		});
	}
	

}
