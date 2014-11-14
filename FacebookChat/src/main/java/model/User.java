package model;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

public class User {
	@FXML HBox item;
	@FXML Label itemLabel1, itemLabel2;
	public void setItem(String a, String b){
		itemLabel1.setText(a);
		itemLabel2.setText(b);
		
	}
	public ListCell<String> getView() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
