package com.light.messenger.client;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import com.light.messenger.MessengerServerInf;
import com.light.vo.UserVO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class SearchController implements Initializable {
	@FXML ListView listView;
	private ObservableList<String> userList;
	private MessengerServerInf server;
	private MessengerController messengerController;
	
	public void setMessengerController(MessengerController messengerController) {
		this.messengerController = messengerController;
	}


	public void setServer(MessengerServerInf server) {
		this.server = server;
	}


	public void setUserList(List<String> userList) {
		this.userList = FXCollections.observableArrayList(userList);
		listView.getItems().addAll(this.userList);
	}

	
	
	@FXML
	 public void onclickAdd(ActionEvent event){
		 String id =  (String)listView.getSelectionModel().getSelectedItem();
		 String user = UserVO.getInstance().getId();
		 try {
			server.userSave(id, user);
			listView.getItems().remove(id);
			messengerController.getUserList();
			Scene scene = listView.getScene();
			Stage stage = (Stage) scene.getWindow();
			stage.setScene(scene);
			stage.close();
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		 
	}
	
	
	


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
