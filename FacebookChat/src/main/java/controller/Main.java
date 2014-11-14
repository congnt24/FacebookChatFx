package controller;

import java.io.IOException;
import java.util.List;

import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static String[] id;
	public static FBChatConnection fbConnect=new FBChatConnection();
	@Override
	public void start(final Stage primaryStage) {
		try {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(ClassLoader.getSystemResource("view/login.fxml"));
			Parent root= loader.load();
			Scene scene = new Scene(root);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			final Login_Controller controller=loader.getController();
			controller.login.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent arg0) {
					id=controller.getUser();
					System.out.println(id[0]+" "+id[1]);
					try {
						fbConnect.connect();
						if (fbConnect.login(id[0], id[1])) {
							FXMLLoader loader=new FXMLLoader();
							loader.setLocation(ClassLoader.getSystemResource("view/main.fxml"));
							Parent root;
							try {
								root = loader.load();
								Main_Controller ctrl=loader.getController();
								Scene scene = new Scene(root);
								//

								List<RosterEntry> l = fbConnect.getFriends();
								if(l!=null){
									ctrl.setList(l);
								}
								else{
									System.out.println("NULL"
											+ "");
								}
								
								primaryStage.setScene(scene);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}else{
							fbConnect.disconnect();
						}
					} catch (XMPPException e1) {
						e1.printStackTrace();
					}
					
				}
			});
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
