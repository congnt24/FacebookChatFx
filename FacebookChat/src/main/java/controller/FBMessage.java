package controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;



public class FBMessage extends Stage{
	  private FBChatConnection FBChat ;
	  private XMPPConnection connect;
	  private RosterEntry userFriend ;
	  private ControllerFbMessage controller;
	 public FBMessage (FBChatConnection f,RosterEntry u){
	    	super();
	    	this.FBChat=f;
	    	this.connect =f.getConnect();
	    	userFriend =u;
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/chat.fxml"));
				this.setTitle(u.getName());
				this.setScene(new Scene((Parent) loader.load(),450,400));
				controller =(ControllerFbMessage)loader.<ControllerFbMessage>getController();
				controller.init(f, u);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	    }
	        public boolean equalsEntry (RosterEntry u ){
		    return userFriend.equals(u);
		    }
			public void setMessage(String name, String message) {
			controller.setMessage(name, message);
				
			}

}
