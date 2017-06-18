package com.light.messenger.client;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.JOptionPane;

import com.light.client.file.FileOutWriter;
import com.light.client.file.SendTCPClient;
import com.light.message.client.Client;
import com.light.messenger.MessengerServerInf;
import com.light.vo.UserVO;

public class ChatMainController implements Initializable{
	@FXML Button send_btn;
	@FXML TextArea textArea_chat;
	@FXML TextArea input;
	@FXML Button invite_btn;
	@FXML Slider slider; 
	@FXML BorderPane borderPane;
	@FXML Label id_label;
	@FXML Button close_btn;
	
	private MessengerServerInf server;
	private Client client;
	private String friendId;
	private int chatIdx;
	private Window stage;
	File file;
	
	
	public void setChatIdx(int chatIdx) {
		this.chatIdx = chatIdx;
	}

	public void setChatMainController(MessengerServerInf server) {
		this.server = server;
	}

	public void setName(String friendId) {
		this.friendId = friendId;
	}
	
	public void setServer(MessengerServerInf server) {
		this.server = server;
	}
	
	public void setClient(Client client) {
		
		this.client = client;
		this.client.setTextArea_chat(textArea_chat);
		
	}
	
	
	public void newChat(){
		try {
			
		String myId = UserVO.getInstance().getId();	
		
		chatIdx = server.makeNewChat(myId, friendId);
		
		if(chatIdx>-1){
	
			
		}else{
			JOptionPane.showMessageDialog(null,"접속하지않은사용자입니다","메세지", JOptionPane.PLAIN_MESSAGE);

		}
		
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@FXML 
	public void mouseSend(ActionEvent event){
		String id = UserVO.getInstance().getId();
		String msg =id+"님: "+input.getText();
		
		try {
			
			server.setChatMsg(chatIdx, msg);
		
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		input.clear();
	}
	
	@FXML
	public void enterSend(KeyEvent event){
		if(event.getCode().toString().equals("ENTER")){
			
			String id = UserVO.getInstance().getId();
			String msg =id+"님: "+input.getText();
			
			try {
				
				
				server.setChatMsg(chatIdx, msg);
				
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
		}
			
	}
	
	@FXML
	public void enterSendAfter(KeyEvent event){
		if(event.getCode().toString().equals("ENTER")){
			input.clear();
		}
	}
	
	
	@FXML
	public void inviteFriend(ActionEvent event){
		try {
			String userId = UserVO.getInstance().getId();
			List<String> userList = server.connectionUser(userId);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ChatInviate.fxml"));
			BorderPane pane = (BorderPane) loader.load();
			ChatInviateController chatInviateController = loader.getController();
			chatInviateController.setServer(server);
			
			chatInviateController.setUserList(userList);
			
			chatInviateController.setNewChatIdx(chatIdx);
			
			Scene scene = new Scene(pane, 400, 450);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL); //부모창 못 닫음
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void close(ActionEvent event){
		String userId = UserVO.getInstance().getId();
		try {
			server.chatExit(userId,chatIdx);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		Stage stage = (Stage) close_btn.getScene().getWindow();
		stage.close();
		//this.client.unexport();
	}
	
	
	@FXML
	public void opacity(MouseEvent event){
		Double op = slider.getValue();
		borderPane.getScene().getWindow().setOpacity(op/100);
		
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		input.setWrapText(true);
		input.setPrefWidth(233);
		textArea_chat.setWrapText(true);
		textArea_chat.setPrefWidth(200);
		id_label.setText(UserVO.getInstance().getId()+" 님의 채팅");
		id_label.setFont(Font.font("Impact",12));
	}
	
	public void sendFile (ActionEvent event) throws RemoteException{
		String date = "";
		FileChooser fileChooser = new FileChooser();
		SendTCPClient STCPC = new SendTCPClient();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("All Files", "*.*");
        fileChooser.getExtensionFilters().add(extFilter);
       
        file = fileChooser.showOpenDialog(null);
        
        	Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						server.fileboo(file.getName());
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					
				}
			});
        thread.start();
        STCPC.clientRun(file.getPath());
        String userId = UserVO.getInstance().getId();
        date = userId +":님이 "+file.getName()+" 파일을 업로드하였습니다.";
        server.setChatMsg(chatIdx,date);
	}
	
	public void receiveFile(ActionEvent event) throws RemoteException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialFileName(server.filenamert());
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"All Files", "*.*");
		fileChooser.getExtensionFilters().add(extFilter);
		final File file = fileChooser.showSaveDialog(stage);
		try {
			server.fileboo2(UserVO.getInstance().getId(), file.getPath());
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}
	
	public void FileWriter (ActionEvent event) throws RemoteException{
		String Time = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
		FileOutWriter a = new FileOutWriter();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialFileName(UserVO.getInstance().getId()+"_"+Time);
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        final File file = fileChooser.showSaveDialog(stage);
		
		a.fileWrite(textArea_chat.getText(), file.getPath());
		
	}

}
	

