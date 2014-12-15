package controller;

import java.awt.Checkbox;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
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
import org.jivesoftware.smackx.packet.VCard;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Main_Controller  implements Runnable, Initializable{
	public static ChatDialog testDialog;
	@FXML ListView<RosterEntry> listView;
	@FXML CheckBox check;
	@FXML Button   btnRefresh;
	@FXML TextField filter;
	@FXML Label stateConnect;
	@FXML MenuItem close;
	@FXML MenuItem logout;
	Roster roster;
	Image on;
	Image off;
	private FBChatConnection FBChat ;
	private XMPPConnection connect;
	private FBMessageListener fbml;
	private ArrayList<FBMessage> listFrameChat = new ArrayList<FBMessage>();
	private BidiMap friends = new DualHashBidiMap();
	private BidiMap friendsinlist = new DualHashBidiMap();
	private Stage primaryStage ;
	private boolean running =true;
	private String pass;
	private String username;
	ObservableList<RosterEntry> listViewItem=FXCollections.observableArrayList();
	
	public void setList(List<RosterEntry> listViewItem){
		this.listViewItem.addAll(listViewItem);
		listView.setItems(this.listViewItem);
	}
	
	
public void init() {
		stateConnect.setText("");
		connect = FBChat.getConnect();
		this.fbml =FBChat.getMessageListener();
		addListFriend();
		Thread thread = new Thread(this);
		thread.setDaemon(true);
		//thread.setPriority(Thread.MIN_PRIORITY);
		thread.start();
		//Platform.runLater(this);
	}
	
public void setFBChatManage(FBChatConnection f){
	FBChat =f;
}
public void setStage(Stage s){
	this.primaryStage=s;
	
}
public void setUsername(String name){
	this.username= name;
}
public void setPass(String pass){
	this.pass= pass;
}
private synchronized void addListFriend(){
	listViewItem =FXCollections.observableArrayList();
    friends.clear();
    friendsinlist.clear();
    Task addlist1 = new Task<Void>() {
  	    public void run() {
  			// TODO Auto-generated method stub
  	    listView.setItems(listViewItem);
  	    stateConnect.setText("");	
  			
  		}

  		@Override
  		protected Void call() throws Exception {
  			// TODO Auto-generated method stub
  			return null;
  		}
  	};
  	Task addlist2 = new Task<Void>() {
  	    public void run() {
  			// TODO Auto-generated method stub
  	    listView.setItems(listViewItem);
  	    stateConnect.setText("Mat Ket Noi Server");	
  			
  		}

  		@Override
  		protected Void call() throws Exception {
  			// TODO Auto-generated method stub
  			return null;
  		}
  	};
    if ((connect != null) && (connect.isConnected())) {
         Roster roster = connect.getRoster();
         
         int i = 0;
         int j = 0;
         for (RosterEntry entry : roster.getEntries()) {
            Presence presence = roster.getPresence(entry.getUser());
            if(filter.getText()==""||entry.getName().toLowerCase().contains(filter.getText().toLowerCase())){
            if ((presence != null) 
               && (presence.getType() != Presence.Type.unavailable)) {
               friendsinlist.put(i, entry);
//               items.add((i+1)+":"+entry.getName()+"[Online]");
               listViewItem.add(entry);
               i++;
            }else if (!check.isSelected())
            {
                friendsinlist.put(i, entry);
//                items.add((i+1)+":"+entry.getName());
                listViewItem.add(entry);
                i++;
            
            }
            }
            friends.put(j, entry);
            j++;
         }
     
      fbml.setFriends(friends);
      fbml.setChatFrame(listFrameChat);
     
  	Platform.runLater(addlist1);
    } else   Platform.runLater(addlist2); 
	  
    
    }
@FXML public void handClose(){
this.connect.disconnect();
running= false;
System.exit(0);

}
@FXML public void handLogout(){
	
	try {
		this.connect.disconnect();
		running=false;
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(ClassLoader.getSystemResource("view/login.fxml"));
		Parent root;
		root = loader.load();
		Scene scene = new Scene(root);
	    Login_Controller controller=loader.getController();
	    controller.setUsername(username);
		controller.init(primaryStage);
		this.primaryStage.setScene(scene);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}
@FXML public void handClick(MouseEvent evt){
	
    if (evt.getClickCount() == 2 && !evt.isConsumed()){
        int i=0;
        int index =this.listView.getSelectionModel().getSelectedIndex();
        if(index==-1) index=0;
        System.out.println(index);
        RosterEntry selectEntry = (RosterEntry) friendsinlist.get(index);	

    if(this.listFrameChat.size()==0){
    	System.out.println(selectEntry);
    	System.out.println("Chay den day chu");
    	System.out.println(selectEntry.toString());
        listFrameChat.add(new FBMessage(this.FBChat,selectEntry));
        }
    else {
    for (i=0;i<this.listFrameChat.size();i++)
        if(listFrameChat.get(i).equalsEntry(selectEntry)) break;
    if (i==this.listFrameChat.size()) listFrameChat.add(new FBMessage(this.FBChat,selectEntry));
    
    }
    this.listFrameChat.get(i).show();
   }

	
}

@FXML public void handChangCheck(ActionEvent event){
	this.addListFriend();
	
}
@FXML public void handRefresh(ActionEvent event){
	addListFriend();
}
public void run() {
	// TODO Auto-generated method stub
	while(running){
		ObservableList<RosterEntry> items = this.listView.getItems();
        try {
        	if(this.filter.getText().equals("")){
        	addListFriend();
            System.out.println("Da quet dc");
            }
            Thread.sleep(5000);
		} catch (Exception e) {
			this.listView.setItems(items);
			System.out.println("Loi luong");
			
		}
        
    
}
	
}


public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
//	listView.setItems(listViewItem);
	on = new Image("file:///"+System.getProperty("user.dir")+"/on.png");
	off = new Image("file:///"+System.getProperty("user.dir")+"/off.png");
//	roster=connect.getRoster();
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
//			            name.getStyleClass().add("cache-list-name");
						ImageView iv1 = new ImageView();
						if (connect.getRoster().getPresence(user.getUser()).getType()==Presence.Type.available) {
					         iv1.setImage(on);
						}else{
					         iv1.setImage(off);
						}
						grid.add(name, 1, 0);
						grid.add(iv1, 0, 0);
//						Label icon = new Label(GeocachingIcons.getIcon(cache).toString());
//			            icon.setFont(Font.font("FontAwesome", FontWeight.BOLD, 24));
//			            icon.getStyleClass().add("cache-list-icon");
//			            grid.add(icon, 0, 0, 1, 2);     
						setGraphic(grid);
					}
				}
				
			};
			return cell;
		}
	});
	
}


}

