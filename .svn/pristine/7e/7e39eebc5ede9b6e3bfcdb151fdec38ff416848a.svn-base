package com.light.hompage.client;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import com.light.client.file.ImageReceiveClient;
import com.light.messenger.MessengerServerInf;
import com.light.vo.MemVO;
import com.sun.javafx.css.parser.LadderConverter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ProfileController implements Initializable {
	
	private MessengerServerInf server;
	private String homepageMember;
	private String color;

	@FXML private Label label_name;
	@FXML private Label label_bir;
	@FXML private Label label_gender;
	@FXML private Label label_nic;
	@FXML private Label label_id;
	@FXML private Label label_email;
	@FXML private Label label_num;
	@FXML private ImageView image_pro;

	public void setColor(String color) {
		this.color = color;
	}

	public String getHomepageMember() {
		return homepageMember;
	}

	public void setHomepageMember(String homepageMember) {
		this.homepageMember = homepageMember;
	}

	public void setServer(MessengerServerInf server) {
		this.server = server;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {	
	}
	
	public void setData() throws RemoteException{
		int rnd = (int)(Math.random()*(9999-1000+1))+1;
		MemVO vo = new MemVO();
		vo.setMemId(homepageMember);
		List<MemVO> list = server.selectmemList(vo);
		vo = list.get(0);
		label_bir.setText(vo.getMemBir().substring(0, 10));
		label_email.setText(vo.getMemEmail());
		label_gender.setText(vo.getMemGn());
		label_id.setText(vo.getMemId());
		label_name.setText(vo.getMemNm());
		label_nic.setText(vo.getMemNic());
		label_num.setText(vo.getMemPhonum());
		label_bir.setTextFill(Color.web(color));
		label_email.setTextFill(Color.web(color));
		label_gender.setTextFill(Color.web(color));
		label_id.setTextFill(Color.web(color));
		label_name.setTextFill(Color.web(color));
		label_nic.setTextFill(Color.web(color));
		label_num.setTextFill(Color.web(color));
		if(!(vo.getMemImg() ==null)){
			server.sendImg(vo.getMemImg(),rnd);
			ImageReceiveClient imgrc = new ImageReceiveClient();
			image_pro.setImage(imgrc.clientRun(rnd));
		}
	}

}
