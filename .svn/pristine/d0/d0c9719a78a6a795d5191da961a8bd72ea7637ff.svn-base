package com.light.hompage.client;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import com.light.client.file.ImageReceiveClient;
import com.light.manager.client.BoardController;
import com.light.messenger.MessengerServerInf;
import com.light.vo.ImgVO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PhotoController4 implements Initializable {
	private ImgVO vo = new ImgVO();
	private MessengerServerInf server;
	private String homepageMember;
	@FXML Label label_title;
	@FXML ImageView image_main;
	private PhtoConutroller phoConutroller;
	public void setPhoConutroller(PhtoConutroller phoConutroller) {
		this.phoConutroller = phoConutroller;
	}
	public void setServer(MessengerServerInf server) {
		this.server = server;
	}
	public void setHomepageMember(String homepageMember) {
		this.homepageMember = homepageMember;
	}
	public void setVo(ImgVO vo) {
		this.vo = vo;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		
	}
	public void setData() throws RemoteException{
		int rnd = (int)(Math.random()*(9999-1000+1))+1;
		label_title.setText(vo.getImgfraTit());
		server.sendImg(vo.getImgfraImg(), rnd); //경로 입력
		ImageReceiveClient imgrc = new ImageReceiveClient(); 
		image_main.setImage(imgrc.clientRun(rnd));
		image_main.setFitHeight(106);
		image_main.setFitWidth(193);
	}
	@FXML
	public void imageViewClick(MouseEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Phto3.fxml"));
		BorderPane pane = (BorderPane)loader.load();
		Scene scene = new Scene(pane,700,700);
		PhotoController3 photoController = loader.getController();
		photoController.setVo(vo);
		photoController.setServer(server);
		photoController.setHomepageMember(homepageMember);
		photoController.setData();
		photoController.setComment();
		photoController.setPhoConutroller4(this);
		photoController.setPhotoController(phoConutroller);
		
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		
	}

}
