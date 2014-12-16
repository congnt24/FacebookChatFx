package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Callback;

import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

public class ControllerFbMessage   implements Initializable{
	@FXML WebView txtTableMessage ;
	private WebEngine webEngine;
	@FXML private TextField txtNewMessage ;
    @FXML private Button   send;
          private FBChatConnection FBChat ;
      	  private XMPPConnection connect;
      	  private RosterEntry userFriend ;
		private String item="";

		@FXML ListView<String> listView;
			ObservableList<String> listItem=FXCollections.observableArrayList();
    public void init( FBChatConnection f,RosterEntry u){
    	FBChat =f;
    	connect = f.getConnect();
    	this.userFriend =u;
    	send.setDefaultButton(true);
    }  	  
    @FXML public void sendMessage (ActionEvent event){
    	 try {
    		if(connect.isConnected()){
    		if(this.txtNewMessage.getText().length()>0){
			this.FBChat.sendMessage(userFriend,this.txtNewMessage.getText());
//			this.txtTableMessage.setText(this.txtTableMessage.getText()+"\n"+connect.getUser().split("@")[0]+" : "+this.txtNewMessage.getText());
			appendText(connect.getUser().split("@")[0], this.txtNewMessage.getText());
	        this.txtNewMessage.setText("");
    		} 
    		}else{ 
//    			this.txtTableMessage.setText(this.txtTableMessage.getText()+"\n"+"Mat Ket Noi Voi May Chu");
    			this.txtNewMessage.setText("");	
    			}
		} catch (XMPPException e) {
//			this.txtTableMessage.setText(this.txtTableMessage.getText()+"\n"+"Mat Ket Noi Voi May Chu");	
			this.txtNewMessage.setText("");
		}
         
    	
    }
    public void setMessage(String name, String message){
//    	this.txtTableMessage.setText(this.txtTableMessage.getText()+"\n"+txt);
    	appendText2(name, message);
    }
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		webEngine=txtTableMessage.getEngine();
		webEngine.loadContent(
				"<html><body bgcolor='#ebebeb' style='font-size:18px'>"
				+ "<div id='content'>"
				+ item
				+ "</div></body></html>");
		
		for (final String smiley : SmileyHandler.getSmileyStrings2()) {
			listItem.add(smiley);
        }
		listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
	
			public ListCell<String> call(ListView<String> arg0) {
				ListCell<String> cell=new ListCell<String>(){
	
					@Override
					protected void updateItem(String user, boolean empty) {
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
							ImageView iv1 = new ImageView();
						    iv1.setImage(new Image("file:///"+System.getProperty("user.dir")+"/src/main/java/smiley/"+SmileyHandler.emoticons2.get(user)+".png"));
							grid.add(iv1, 0, 0);
							setGraphic(grid);
						}
					}
					
				};
				return cell;
			}
		});
		listView.setOrientation(Orientation.HORIZONTAL);
		listView.setItems(listItem);
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
		        System.out.println(newValue);
		        txtNewMessage.appendText(newValue);
			}
		});
	}
	
	public void appendText(String name, String message){
//		Document doc=webEngine.getDocument();
//		Element ele=doc.getElementById("content");
//		String oldMess=ele.getTextContent();
//		ele.setTextContent(oldMess+"<br>"+name+" : "+message);
		item+="<p align=\"right\" style=\"BACKGROUND-COLOR: #dfe3ee\">"
				+ SmileyHandler.getSmiledText(message)+" : "+name
				+ "</p>";
		webEngine.loadContent(
				"<html><body bgcolor='#ebebeb' style='font-size:18px'>"
				+ "<div id='content'>"
				+ item
				+ "</div></body></html>");
		
	}
	public void appendText2(String name, String message){
		item+="<p style=\"BACKGROUND-COLOR: #e5e7eb\">"
				+ name+" : "+SmileyHandler.getSmiledText(message)
				+ "</p>";
		webEngine.loadContent(
				"<html><body bgcolor='#ebebeb' style='font-size:18px'>"
				+ "<div id='content'>"
				+ item
				+ "</div></body></html>");
		
	}

	
    
}

