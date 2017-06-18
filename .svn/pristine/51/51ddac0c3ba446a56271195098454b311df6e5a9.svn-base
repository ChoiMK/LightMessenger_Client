package com.light.messenger.client;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import com.light.messenger.MessengerServerInf;
import com.light.vo.MsgVO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MsgDetailController implements Initializable {
	@FXML Label label_send;
	@FXML Label label_resive;
	@FXML Label label_date;
	@FXML Label label_content;
	@FXML ImageView img_new;
	@FXML Button button_read;
	@FXML Button button_delete;
	public void setParentController(MsglockerController parentController) {
		this.parentController = parentController;
	}
	private MsgVO vo;
	private MessengerServerInf server;
	private MsglockerController parentController;

	public void setVo(MsgVO vo) {
		this.vo = vo;
	}
	public void setServer(MessengerServerInf server) {
		this.server = server;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	public void setButton(){
		button_read.visibleProperty().set(false);
		button_delete.visibleProperty().set(false);
	}
	public void setimage(){
		img_new.setVisible(false);
	}
	public void setData(){
		label_send.setText(vo.getMsgSenmem());
		label_resive.setText(vo.getMsgRecmem());
		label_date.setText(vo.getMsgDt());
		label_content.setText(vo.getMsgHt());
	}
	
	
	@FXML
	public void detail(MouseEvent event) throws IOException{
		if(vo.getMsgWtr().equals("NO")){
			vo.setMsgWtr("YES");
			img_new.setVisible(false);
			server.updateMsg(vo);
		}
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MsgDtail2.fxml"));
		AnchorPane pane = loader.load();
		MsgDtail2Controller msgDtail2Controller = loader.getController();
		msgDtail2Controller.setServer(server);
		msgDtail2Controller.setVo(vo);
		msgDtail2Controller.setParentController(parentController);
		msgDtail2Controller.setData();
		Scene scene = new Scene(pane,400,400);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	public void detail2(ActionEvent event) throws IOException{
		detail(null);
	}
	public void delete(ActionEvent event) throws IOException{
		server.deleteMsg(vo.getMsgNum());
		parentController.setData();
	}

}
