package controller;

/*import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.MapIterator;*/
 
import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;
 
public class FBMessageListener implements MessageListener, Runnable {
 
   private FBMessageListener fbml = this;
   private XMPPConnection conn;
//   private BidiMap friends;
   private List<RosterEntry> listFriends=new ArrayList<RosterEntry>();
 
   public FBMessageListener(XMPPConnection conn) {
      this.conn = conn;
      new Thread(this).start();
   }
 
   public void setFriends(List<RosterEntry> listFriends) {
      this.listFriends=listFriends;
   }
 
   public void processMessage(Chat chat, Message message) {
      RosterEntry entry = null; // Lưu người gửi
      
	   for (RosterEntry rosterEntry : listFriends) {
		if (rosterEntry.getUser().equalsIgnoreCase(chat.getParticipant())) {
			entry=rosterEntry;
		}
	}
      if ((message != null) && (message.getBody() != null)) {
    	  
         System.out.println(entry.getName()+" : "+message.getBody());
      }
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