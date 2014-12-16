package controller;

import java.awt.event.TextEvent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

public class ControllerFbMessage   {
	@FXML private TextArea txtTableMessage ;
	@FXML private TextField txtNewMessage ;
    @FXML private Button   send;
          private FBChatConnection FBChat ;
      	  private XMPPConnection connect;
      	  private RosterEntry userFriend ;
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
			this.txtTableMessage.setText(this.txtTableMessage.getText()+"\n"+connect.getUser().split("@")[0]+" : "+this.txtNewMessage.getText());
	        this.txtNewMessage.setText("");
    		} 
    		}else{ this.txtTableMessage.setText(this.txtTableMessage.getText()+"\n"+"Mat Ket Noi Voi May Chu"); this.txtNewMessage.setText("");	}
		} catch (XMPPException e) {
			this.txtTableMessage.setText(this.txtTableMessage.getText()+"\n"+"Mat Ket Noi Voi May Chu");	
			this.txtNewMessage.setText("");
		}
         
    	
    }
    public void setMessage(String txt){
    	this.txtTableMessage.setText(this.txtTableMessage.getText()+"\n"+txt);
    }
    
}

