package controller;

import java.awt.event.TextEvent;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ControllerFbMessage   implements Initializable{
	@FXML WebView txtTableMessage ;
	private WebEngine webEngine;
	@FXML private TextField txtNewMessage ;
    @FXML private Button   send;
          private FBChatConnection FBChat ;
      	  private XMPPConnection connect;
      	  private RosterEntry userFriend ;
		private String item="";
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
	}
	public void appendText(String name, String message){
//		Document doc=webEngine.getDocument();
//		Element ele=doc.getElementById("content");
//		String oldMess=ele.getTextContent();
//		ele.setTextContent(oldMess+"<br>"+name+" : "+message);
		item+="<p align=\"right\" style=\"BACKGROUND-COLOR: #8b9dc3\">"
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
//	public String parserMessage(String message){
//		String result="";
//        int index;
//        for (index = 0; index < builder.length(); index++) {
//            for (final String smiley : getSmileyStrings()) {
//                final int length = smiley.length();
//                if (index + length > builder.length()) {
//                    continue;
//                }
//                if (builder.subSequence(index, index + length).toString()
//                        .equals(smiley)) {
//                    builder.setSpan(new ImageSpan(context,
//                                    getSmileyDrawable(smiley)), index, index + length,
//                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                    index += length - 1;
//                    break;
//                }
//            }
//        }
//		return result;
//	}
	
    
}

