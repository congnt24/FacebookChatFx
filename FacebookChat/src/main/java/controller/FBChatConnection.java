package controller;

import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;
 
public class FBChatConnection {
 
   public static final String FB_XMPP_HOST = "chat.facebook.com";
   public static final int FB_XMPP_PORT = 5222;
 
   private ConnectionConfiguration config;
   private XMPPConnection connection;
   private FBMessageListener fbml;
 
   public boolean connect() throws XMPPException {
      config = new ConnectionConfiguration(FB_XMPP_HOST, FB_XMPP_PORT);
      SASLAuthentication.registerSASLMechanism("DIGEST-MD5", CustomSASLDigestMD5Mechanism.class);
      config.setSASLAuthenticationEnabled(true);
      config.setDebuggerEnabled(false);
      connection = new XMPPConnection(config);
      connection.connect();
      fbml = new FBMessageListener(connection);
      return connection.isConnected();
   }
 
   public void disconnect() {
      if ((connection != null) && (connection.isConnected())) {
         Presence presence = new Presence(Presence.Type.unavailable);
         presence.setStatus("offline");
         connection.disconnect(presence);
      }
   }
 
   public boolean login(String userName, String password) 
     throws XMPPException {
      if ((connection != null) && (connection.isConnected())) {
         connection.login(userName, password);
         return true;
      }
      return false;
   }
 
   //Lấy danh sách bạn bè
   public List<RosterEntry> getFriends() {
	   System.out.println(connection+" .."+connection.isConnected());
	  List<RosterEntry> listFriends=new ArrayList<RosterEntry>();
      if ((connection != null) && (connection.isConnected())) {
         Roster roster = connection.getRoster();
         for (RosterEntry entry : roster.getEntries()) {
            Presence presence = roster.getPresence(entry.getUser());
            if ((presence != null) && (presence.getType() != Presence.Type.unavailable)) {
            	listFriends.add(entry);
            }
         }
         fbml.setFriends(listFriends);
         return listFriends;
      }
	return null;
   }
   public void sendMessage(RosterEntry friend, String text) 
     throws XMPPException {
      if ((connection != null) && (connection.isConnected())) {
         ChatManager chatManager = connection.getChatManager();
         Chat chat = chatManager.createChat(friend.getUser(), fbml);
         chat.sendMessage(text);
         System.out.println("Your message has been sent to "+ friend.getName());
      }
   }
   
  /* public static void main(String[] args) {
      if (args.length == 0) {
        System.err.println("Usage: java FBConsoleChatApp [username_facebook] [password]");
        System.exit(-1);
      }
 
      String username = "trungcong24";
      String password = "n24t89c43";
 
      FBConsoleChatApp app = new FBConsoleChatApp();
 
      try {
         app.connect();
         if (!app.login(username, password)) {
            System.err.println("Access Denied...");
            System.exit(-2);
         }
         app.showMenu();
         String data = null;
         menu:
         while((data = app.readInput().trim()) != null) {
            if (!Character.isDigit(data.charAt(0))) {
               System.out.println("Invalid input.Only 1-3 is allowed !");
               app.showMenu();
               continue;
            }
            int choice = Integer.parseInt(data);
            if ((choice != 1) && (choice != 2) && (choice != 3)) {
               System.out.println("Invalid input.Only 1-3 is allowed !");
               app.showMenu();
               continue;
            }
            switch (choice) {
               case 1: app.getFriends();
                       app.showMenu();
                       continue menu;
               case 2: app.sendMessage();
                       app.showMenu();
                       continue menu;
               default: break menu;
            }
         }
         app.disconnect();
      } catch (XMPPException e) {
        if (e.getXMPPError() != null) {
           System.err.println("ERROR-CODE : " + e.getXMPPError().getCode());
           System.err.println("ERROR-CONDITION : " + e.getXMPPError().getCondition());
           System.err.println("ERROR-MESSAGE : " + e.getXMPPError().getMessage());
           System.err.println("ERROR-TYPE : " + e.getXMPPError().getType());
        }
        app.disconnect();
      } catch (IOException e) {
        System.err.println(e.getMessage());
        app.disconnect();
      }
  }*/
}