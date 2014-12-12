package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.jivesoftware.smack.XMPPException;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Login_Controller implements Runnable{
	@FXML Button login;
	@FXML TextField username;
	@FXML PasswordField password;
	@FXML Label   txtLogin;
	@FXML MenuItem close;
	@FXML CheckBox remember;
	@FXML BorderPane loginpane;
	private Scene scene;
    private Parent root;
    private Stage primaryStage ;
    private Thread thread1,thread2;
    FBChatConnection app ;
    private Main_Controller ls;
    private EventHandler<KeyEvent> keyListener;
    private BufferedReader in;
	private BufferedWriter out;
    Task processlogin = new Task<Void>() {
	    public void run() {
			// TODO Auto-generated method stub
	    	if(txtLogin.getText().length()<24)  txtLogin.setText(txtLogin.getText()+" ."); 
			else  txtLogin.setText("Dang Dang Nhap");
		
			
		}

		@Override
		protected Void call() throws Exception {
			// TODO Auto-generated method stub
			return null;
		}
	};
 
	public void init(Stage s) {
    primaryStage =s;
    txtLogin.setText("");
    login.setDefaultButton(true);
    try {
    	String str;
		in = new BufferedReader(new FileReader("data.txt"));
		str= in.readLine();
		if(str!=null && str.equals("1")) {
		 this.remember.setSelected(true);
		 this.username.setText(in.readLine());
		 this.password.setText(in.readLine());
		}
		in.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}catch (IOException e1){
		
	}
    
    try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main.fxml"));
		scene = new Scene((Parent) loader.load(),300,600);
		ls =(Main_Controller)loader.<Main_Controller>getController();
		ls.setStage(primaryStage);
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    primaryStage.setOnHiding(new EventHandler<WindowEvent>() {

        public void handle(WindowEvent event) {
        	if(!remember.isSelected()){
        		 try{
 			    	File file = new File("data.txt");
 			    	if (!file.exists()) {
 						file.createNewFile();
 					}
 		 
 					FileWriter fw = new FileWriter(file.getAbsoluteFile());
 					BufferedWriter bw = new BufferedWriter(fw);
 					bw.write("0");
 					bw.close();
 			    } catch (IOException e) {
 					e.printStackTrace();
 				}
        		
        	}
        	
        }
    });
	
		
	}
	
public void setUsername(String name){
	this.username.setText(name);
}
public void setPass(String pass){
	this.password.setText(pass);
}
@FXML public void handClose(){
	if(!remember.isSelected()){
		 try{
	    	File file = new File("data.txt");
	    	if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("0");
			bw.close();
	    } catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	System.exit(0);
}	
	
@FXML public void handLogin(){
	 if(username.getText().length()>0 && password.getText().length()>0){
	  txtLogin.setText("Dang Dang Nhap");
	  login.setDisable(true);
	  username.setDisable(true);
	  password.setDisable(true);
	  remember.setDisable(true);
	  
	  Task task2 = new Task<Void>() {
		    public void run() {
				// TODO Auto-generated method stub
		 
		   while (true){
		   
		   try {
			Platform.runLater(processlogin);
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   }	
			
				
			}

			@Override
			protected Void call() throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
		};
		thread2= new Thread(task2);
		thread2.start();
		thread1 =new Thread(this);
		thread1.start();
		  
	 } else  txtLogin.setText("Ban chua nhap Usename hoac Pass");
		
	}
public void run() {
	// TODO Auto-generated method stub
	Task errorlogin = new Task<Void>() {
	    public void run() {
			// TODO Auto-generated method stub
	    	
	    	thread2.stop();
	    	while(thread2.isAlive()){}
	    	login.setDisable(false);
	    	username.setDisable(false);
	  	    password.setDisable(false);
	  	    remember.setDisable(false);
	    	txtLogin.setText("Sai Tai Khoan,Hoc Mk");
	    	System.out.println("Chay den day roi . .");
	    	
			
		}

		@Override
		protected Void call() throws Exception {
			// TODO Auto-generated method stub
			return null;
		}
	};
	Task notconnect = new Task<Void>() {
	    public void run() {
			// TODO Auto-generated method stub
	    	
	    	thread2.stop();
	    	while(thread2.isAlive()){}
	    	login.setDisable(false);
	    	username.setDisable(false);
	  	    password.setDisable(false);
	  	    remember.setDisable(false);
	    	txtLogin.setText("Khong the ket noi may chu");
	    	System.out.println("Khong the ket noi may chu .");
	    	
			
		}

		@Override
		protected Void call() throws Exception {
			// TODO Auto-generated method stub
			return null;
		}
	};
	app = new FBChatConnection();
	try {
	if (app.connect())
	{
    if (app.login(username.getText(),password.getText())){
            ls.setFBChatManage(app);
			ls.init();
			ls.setUsername(this.username.getText());
			ls.setPass(this.password.getText());
			Task tasklogin = new Task<Void>() {
			    public void run() {
					// TODO Auto-generated method stub
			    try{
			    	File file = new File("data.txt");
			    	if (!file.exists()) {
						file.createNewFile();
					}
		 
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					if(remember.isSelected()){
					bw.write("1");
					bw.newLine();
					bw.write(username.getText());
					bw.newLine();
					bw.write(password.getText());
					
					} else bw.write("0\n\r");
					bw.close();
			    } catch (IOException e) {
					e.printStackTrace();
				}
			    	
			    	primaryStage.setScene(scene);
					primaryStage.show();
					thread2.stop();
				
					
				}

				@Override
				protected Void call() throws Exception {
					// TODO Auto-generated method stub
					return null;
				}
			};
			Platform.runLater(tasklogin);
		
		
		
	
    }else 
    {
    
    Platform.runLater(errorlogin);
    app.disconnect();
   
    }	
	} 
	} catch (XMPPException e1) {
		if(e1.toString().contains("not-authorized"))  Platform.runLater(errorlogin);
		else Platform.runLater(notconnect);
		app.disconnect();
		
	}
	
}

}
