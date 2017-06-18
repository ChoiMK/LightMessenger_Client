package com.light.messenger.client;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import com.light.messenger.MessengerServerInf;
import com.light.vo.MsgVO;
import com.light.vo.UserVO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class SendMsgController implements Initializable {
	@FXML Label label_name;
	@FXML TextArea textArea;
	private MessengerServerInf server;
	private String name;
	
	public void setName(String name) {
		this.name = name;
		label_name.setText(name);
	}

	public void setServer(MessengerServerInf server) {
		this.server = server;
	}
	
	public void send(ActionEvent event){
		
		
		String snedUser = UserVO.getInstance().getId();
		String receiveUser = this.name;
		String content = textArea.getText();
		
		try {
			server.setMsg(snedUser, receiveUser, content);
			
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}finally{
			Stage stage = (Stage)textArea.getScene().getWindow();
			stage.close();
		}
		
		
	}
	
	public void cancel(ActionEvent event){
		Stage stage = (Stage)textArea.getScene().getWindow();
		stage.close();
	}
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
}
