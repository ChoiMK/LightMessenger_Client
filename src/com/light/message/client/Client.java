package com.light.message.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

import application.MainController;
import application.MyController;
import application.UserController;

import com.light.client.file.ReceiveTCPClient;
import com.light.messenger.MessengerClientInf;
import com.light.messenger.MessengerServerInf;
import com.light.messenger.client.ChatMainController;
import com.light.messenger.client.MessengerController;
import com.light.vo.FdVO;
import com.light.vo.UserVO;

public class Client extends UnicastRemoteObject implements MessengerClientInf {
	private TextArea textArea_chat;
	private MessengerServerInf server;
	private ListView listview;

	MessengerController messengerController;
	

	public void setMessengerController(MessengerController mess) {
		this.messengerController = mess;
	}


	public void unexport(){
		try {
			UnicastRemoteObject.unexportObject(this, true);
		} catch (NoSuchObjectException e) {
			e.printStackTrace();
		}
	}


	public Client() throws RemoteException {
		try {
			server =(MessengerServerInf)Naming.lookup("rmi://192.168.201.252/chat");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public void setTextArea_chat(TextArea textArea_chat) {
		this.textArea_chat = textArea_chat;
	
	}
	
	
		
	@Override
	public void setMsg(final String snedUser, final String receiveUser,
			final String content) throws RemoteException {
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				JOptionPane.showMessageDialog(null, content, "보낸이 " + snedUser, JOptionPane.PLAIN_MESSAGE);
			}

		});
		thread.start();
	}


	@Override
	public void chatInvite(final int newChatIdx, final String sendId, final String receveId, final ArrayList<MessengerClientInf> chatList, final ArrayList<String> chatUserList) throws RemoteException {
		Platform.runLater(new Runnable() {
			int check = 0;
			
			@Override
			public void run() {
			
			 check = JOptionPane.showConfirmDialog(null, sendId + "님께서 초대메세지를 보내셨습니다.", "초대메세지",
								JOptionPane.YES_NO_OPTION);
				
				if (check == 1) {
					
					String msg = receveId+"님이 거절하셨습니다.";
					try {
						server.setChatMsg(newChatIdx, msg);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					
				} else {
					
					String msg = receveId+"님이 입장하셨습니다.";	
					
					try {
						server.setChatMsg(newChatIdx, msg);
						chatLoad(newChatIdx);
					} catch (RemoteException e) {
						e.printStackTrace();
					} 
				}
				try {
					server.joinChat(check, newChatIdx, receveId, chatList, chatUserList);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		
		});		
		
	}	
		
	@Override
	public void setChatMsg(String msg) throws RemoteException {
		
		textArea_chat.setText(this.textArea_chat.getText()+"\n"+msg);
		
		//자동스크롤
		this.textArea_chat.setScrollTop(Double.MAX_VALUE);
		this.textArea_chat.selectPositionCaret(textArea_chat.getLength()); 
		this.textArea_chat.deselect(); 
	}
	
	@Override
	public void chatLoad(int chatIdx) throws RemoteException {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/light/messenger/client/ChatMain.fxml"));
			BorderPane root = (BorderPane)loader.load();
			this.textArea_chat=(TextArea)root.lookup("#textArea_chat");
			
			ChatMainController chatMainController = loader.getController();
			
			chatMainController.setChatMainController(server);
			chatMainController.setChatIdx(chatIdx);
			root.getStylesheets().add("/com/light/view/styles/Messenger_style.css");
			
			Scene scene = new Scene(root,350,550);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void resivefile(String run) throws RemoteException{
		ReceiveTCPClient RTCP = new ReceiveTCPClient();
		RTCP.clientRun(run);
		
	}

	@Override
	public void getUserList() {
		
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				messengerController.getUserList();
			}
		});

	}
}
