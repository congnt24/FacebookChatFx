package controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import org.apache.commons.collections.BidiMap;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;



public class FBMessageListener  implements MessageListener,Runnable {

	   private FBMessageListener fbml = this;
	   private XMPPConnection conn;
	   private FBChatConnection FBchat;
	   private BidiMap friends;
	   private ArrayList<FBMessage> listFrameChat ;
	   private FBMessage cv;
	 
	   public FBMessageListener(FBChatConnection f) {
	      FBchat =f;
	      this.conn = f.getConnect();
	     // new Thread(this).start();
	      Platform.runLater(this);
	   }
	 
	   public void setFriends(BidiMap friends) {
	      this.friends = friends;
	   }
	   public void setChatFrame( ArrayList<FBMessage> l){
	   this.listFrameChat = l;
	   }
	 
	   public void processMessage(Chat chat, Message message) {
	      System.out.println("Co tin nhan moi .");
	      //MapIterator it = friends.mapIterator();
	      int j;
	      String key = null;
	      RosterEntry entry = null;

	      for(j=0;j<friends.size();j++){
	      entry = (RosterEntry) friends.get(j+1);
	       if (entry.getUser().equalsIgnoreCase(chat.getParticipant()))  break;
	      }
	      if(j==friends.size())entry=null;
	      System.out.println("chay dc den day chua:"+entry.getName());
	      
	      
	      Platform.runLater(new TaskView(FBchat,friends,listFrameChat,message,entry)); 
	      playmp3();
	   }
	 private void playmp3(){
		 String uriString = new File("src\\main\\java\\mp3\\ring.mp3").toURI().toString();
     	 MediaPlayer player = new MediaPlayer( new Media(uriString));
         player.play();
		 
	 }
	   public void run() {
	         conn.getChatManager().addChatListener(
	         new ChatManagerListener() {
	            public void chatCreated(Chat chat, boolean createdLocally) {
	               if (!createdLocally) {
	                  chat.addMessageListener(fbml);
	               }
	            }
	         }
	      );
	   }

	

}
