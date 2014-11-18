package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Presence;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class Main_Controller  implements Runnable{
	public static ChatDialog testDialog;
	@FXML ListView listView;
	private FBChatConnection FBChat ;
	private XMPPConnection connect;
	private FBMessageListener fbml;
	private ArrayList<FBMessage> listFrameChat = new ArrayList<FBMessage>();
	private BidiMap friends = new DualHashBidiMap();
	ObservableList<RosterEntry> listViewItem=FXCollections.observableArrayList();
	
	public void setList(List<RosterEntry> listViewItem){
		this.listViewItem.addAll(listViewItem);
		listView.setItems(this.listViewItem);
	}
	/*public void initialize(URL arg0, ResourceBundle arg1) {
		listView.setItems(listViewItem);
		listView.setCellFactory(new Callback<ListView<RosterEntry>, ListCell<RosterEntry>>() {

			public ListCell<RosterEntry> call(ListView<RosterEntry> arg0) {
				ListCell<RosterEntry> cell=new ListCell<RosterEntry>(){

					@Override
					protected void updateItem(RosterEntry user, boolean empty) {
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
							Label name=new Label(user.getName());
//				            name.getStyleClass().add("cache-list-name");
							Label username=new Label(user.getUser());
							grid.add(name, 0, 0);
							grid.add(username, 0, 1);
//							Label icon = new Label(GeocachingIcons.getIcon(cache).toString());
//				            icon.setFont(Font.font("FontAwesome", FontWeight.BOLD, 24));
//				            icon.getStyleClass().add("cache-list-icon");
//				            grid.add(icon, 0, 0, 1, 2);     
							setGraphic(grid);
						}
					}
					
				};
				return cell;
			}
		});
		listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent click) {
				// TODO Auto-generated method stub
				if (click.getClickCount()==2) {
					RosterEntry entry=listView.getSelectionModel().getSelectedItem();
					testDialog = new ChatDialog(entry);
					testDialog.showAndWait();
				}
			}
		});
	}*/
	
public void init() {
		
		connect = FBChat.getConnect();
		this.fbml =FBChat.getMessageListener();
		addListFriend();
		Thread thread = new Thread(this);
		thread.setDaemon(true);
		thread.start();
	}
	
public void setFBChatManage(FBChatConnection f){
	FBChat =f;
}
private void addListFriend(){
	ObservableList<String> items =FXCollections.observableArrayList();
    //String str = (String) this.jList.getSelectedValue();
    
    if ((connect != null) && (connect.isConnected())) {
         Roster roster = connect.getRoster();
         int i = 1;
         for (RosterEntry entry : roster.getEntries()) {
            Presence presence = roster.getPresence(entry.getUser());
            if ((presence != null) 
               && (presence.getType() != Presence.Type.unavailable)) {
               friends.put(i, entry);
               Button t =new Button(i+":"+entry.getName());
               t.setVisible(true);
               items.add(i+":"+entry.getName()+"[Online]");
               i++;
            }
            else {
                friends.put(i, entry);
                items.add(i+":"+entry.getName());
                i++;
            
            }
         }
      this.listView.setItems(items);
      // this.jList.setSelectedValue(str,true);
       fbml.setFriends(friends);
       fbml.setChatFrame(listFrameChat);
       
       
       
        
      }
    
    }
@FXML public void handClick(MouseEvent evt){
	int i=0;
    int index =this.listView.getSelectionModel().getSelectedIndex()+1;
    RosterEntry selectEntry = (RosterEntry) friends.get(index);
    if (evt.getClickCount() == 2 && !evt.isConsumed()){
    if(this.listFrameChat.size()==0)
        listFrameChat.add(new FBMessage(this.FBChat,selectEntry));
    else {
    for (i=0;i<this.listFrameChat.size();i++)
        if(listFrameChat.get(i).equalsEntry(selectEntry)) break;
    if (i==this.listFrameChat.size()) listFrameChat.add(new FBMessage(this.FBChat,selectEntry));
    
    }
    this.listFrameChat.get(i).show();
   }

	
}
public void run() {
	// TODO Auto-generated method stub
	while(true){
        
        try {
        	addListFriend();
            System.out.println("Da quet dc");
            Thread.sleep(5000);
		} catch (InterruptedException e) {
			System.out.println("Loi luong");
		}
        
    
}
	
}

}
