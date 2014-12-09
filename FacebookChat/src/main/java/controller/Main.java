package controller;

import java.io.IOException;
import java.util.List;

import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static String[] id;
	@Override
	public void start(final Stage primaryStage) {
		try {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(ClassLoader.getSystemResource("view/login.fxml"));
			Parent root= loader.load();
			Scene scene = new Scene(root);
		    
            Login_Controller controller=loader.getController();
			controller.init(primaryStage);
		
			primaryStage.setScene(scene);
			primaryStage.setTitle("FaceBook Chat");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
