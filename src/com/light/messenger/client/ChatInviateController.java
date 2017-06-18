package com.light.messenger.client;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import com.light.messenger.MessengerServerInf;
import com.light.vo.UserVO;

public class ChatInviateController implements Initializable {
	@FXML ListView listView;
	private MessengerServerInf server;
	private ObservableList<String> userList;
	private int ChatIdx;
	
	public void setNewChatIdx(int ChatIdx) {
		this.ChatIdx = ChatIdx;
	}


	public void setUserList(List<String> userList) {
		this.userList = FXCollections.observableArrayList(userList);
		listView.getItems().setAll(this.userList);
	}


	public void setServer(MessengerServerInf server) {
		this.server = server;
	}
	
	
	@FXML
	public void chatInviate(){
		String sendId = UserVO.getInstance().getId();
		String receveId = (String)listView.getSelectionModel().getSelectedItem();
		try {
			server.inviate(sendId, receveId, ChatIdx);
			listView.getItems().remove(receveId);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	
	}
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
}
