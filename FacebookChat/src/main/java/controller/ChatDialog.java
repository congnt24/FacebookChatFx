package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.SwingUtilities;

import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChatDialog extends Stage implements Initializable{
	FBChatConnection fbConnect=Main.fbConnect;
	RosterEntry entry;
	@FXML
		public Button sendButton;
		public TextArea textArea;
		public TextField textField;
	public ChatDialog(RosterEntry entry) {
		this.entry=entry;
		FXMLLoader loader=new FXMLLoader(ClassLoader.getSystemResource("view/chat.fxml"));
		loader.setController(this);
		try {
			setScene(new Scene((Parent)loader.load()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		sendButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				String message=textField.getText().trim();
				if (message.length()!=0) {
					try {
						fbConnect.sendMessage(entry, message);
						textArea.appendText(String.format("%100s", message)+"\n");
					} catch (XMPPException e) {
						e.printStackTrace();
					}
					textField.setText("");
				}
			}
		});
	}
	public void setMessage(final String message){
		System.out.println(message);
		Platform.runLater(new Runnable() {
			
			public void run() {
				textArea.appendText(String.format("%s", message)+"\n");
			}
		});
	}

}
