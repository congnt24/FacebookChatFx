package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login_Controller implements Initializable {
	@FXML Button login;
	@FXML TextField username;
	@FXML PasswordField password;
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	public String[] getUser(){
		String[] user=new String[2];
		user[0]=username.getText();
		user[1]=password.getText();
		return user;
	}
}
