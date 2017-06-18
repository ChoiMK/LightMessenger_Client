package com.light.messenger.client;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import com.light.messenger.MessengerServerInf;
import com.light.vo.MsgVO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MsgDtail2Controller implements Initializable {
	@FXML private Label label_send;
	@FXML private Label label_resive;
	@FXML private Label label_date;
	@FXML private Label label_content;
	private MsgVO vo;
	private MessengerServerInf server;
	private MsglockerController parentController;
	public void setVo(MsgVO vo) {
		this.vo = vo;
	}

	public void setServer(MessengerServerInf server) {
		this.server = server;
	}

	public void setParentController(MsglockerController parentController) {
		this.parentController = parentController;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setData(){
		label_send.setText(vo.getMsgSenmem());
		label_resive.setText(vo.getMsgRecmem());
		label_date.setText(vo.getMsgDt());
		label_content.setText(vo.getMsgHt());
	}
	@FXML
	public void cancle(ActionEvent event){
		Stage stage = (Stage) label_content.getScene().getWindow();
		stage.close();
	}
	@FXML
	public void delete(ActionEvent event) throws IOException{
		server.deleteMsg(vo.getMsgNum());
		cancle(null);
		parentController.setData();
	}

}
