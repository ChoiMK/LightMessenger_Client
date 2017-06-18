package com.light.hompage.client;


import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.light.messenger.MessengerServerInf;
import com.light.vo.HpVO;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class SetController implements Initializable {

	private MessengerServerInf server;
	private String homepageMember;
	private String color = "";
	@FXML private ColorPicker color_select;
	@FXML private TextArea text_smsg;
	@FXML private TextField text_title;
	private HomeMainController homeMainController;
	
	
	public void setHomeMainController(HomeMainController homeMainController) {
		this.homeMainController = homeMainController;
	}
	
	public void setServer(MessengerServerInf server) {
		this.server = server;
	}
	
	public void setHomepageMember(String homepageMember)  {
		this.homepageMember = homepageMember;
	
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	public void setData() throws RemoteException{
		HpVO vo = new HpVO();
		vo.setHpMem(homepageMember);
		List<HpVO> list = new ArrayList<HpVO>();
		list = server.selectHp(vo);
		vo = list.get(0);
		text_title.setText(vo.getHpMawrt());
		text_smsg.setText(vo.getHpSttwrt());
		color_select.setValue(Color.valueOf(vo.getHpClo()));
		
	}
	
	@FXML
	public void updateHp(ActionEvent event) throws IOException{
		String title = text_title.getText();
		String smsg = text_smsg.getText();
		String color = color_select.getValue().toString();
		color =  "#"+ color.substring(2,8);
		HpVO vo = new HpVO();
		vo.setHpMem(homepageMember);
		List<HpVO> list = new ArrayList<HpVO>();
		list = server.selectHp(vo);
		vo = list.get(0);
		vo.setHpMawrt(title);
		vo.setHpSttwrt(smsg);
		vo.setHpClo(color);
		server.update(vo);
		homeMainController.hompageDataSet();
		homeMainController.onclickLabel(null);
		
	}
	@FXML
	public void cancle(ActionEvent event) throws IOException{
		homeMainController.onclickLabel(null);
	}

}
