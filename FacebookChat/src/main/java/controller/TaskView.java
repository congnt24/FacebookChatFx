package controller;

import java.util.ArrayList;

import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.apache.commons.collections.BidiMap;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.packet.Message;

public class TaskView extends Task<Void>{
	private FBChatConnection FBchat;
	private BidiMap friends;
	private ArrayList<FBMessage> listFrameChat ;
	Message message ;
	RosterEntry entry ;
    
	public TaskView (FBChatConnection a,BidiMap b,ArrayList c,Message d,RosterEntry e){
		FBchat =a;
		friends =b;
		listFrameChat =c;
		message =d;
		entry =e;
		
	}
	@Override
	protected Void call() throws Exception {
		// TODO Auto-generated method stub
		Stage g = new Stage();
		StackPane root = new  StackPane();
		g.setTitle("New Win Dow");
		g.setScene(new Scene(root, 450, 450));
		g.show();
		return null;
	}
	 @Override public void run() {
		 if ((message != null) && (message.getBody() != null) && entry!=null) {
	        int i=0;
	        if(this.listFrameChat.size()==0)
	            listFrameChat.add(new FBMessage(this.FBchat,entry));
	        else {
	        for (i=0;i<this.listFrameChat.size();i++)
	            if(listFrameChat.get(i).equalsEntry(entry)) break;
	        if (i==this.listFrameChat.size()) listFrameChat.add(new FBMessage(this.FBchat,entry));
	        } 
	       this.listFrameChat.get(i).setMessage(entry.getName(), message.getBody());
	       this.listFrameChat.get(i).show();
	       }   
	        
	       
	    }
	
	

}
