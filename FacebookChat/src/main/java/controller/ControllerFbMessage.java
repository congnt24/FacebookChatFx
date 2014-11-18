package controller;

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
    }  	  
    @FXML public void sendMessage (ActionEvent event){
    	 try {
			this.FBChat.sendMessage(userFriend,this.txtNewMessage.getText());
			this.txtTableMessage.setText(this.txtTableMessage.getText()+"\n"+connect.getUser()+":"+this.txtNewMessage.getText());
	        this.txtNewMessage.setText("");
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
    	
    }
    public void setMessage(String txt){
    	this.txtTableMessage.setText(this.txtTableMessage.getText()+"\n"+txt);
    }
    
}

