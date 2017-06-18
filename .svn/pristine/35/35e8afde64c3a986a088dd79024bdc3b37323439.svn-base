package com.light.hompage.client;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import com.light.messenger.MessengerServerInf;
import com.light.vo.GbkVO;
import com.light.vo.HpVO;
import com.light.vo.UserVO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class GuestBookController2 implements Initializable{

	GuestBookController parentController;
	private Pane main_pane;
	private Pane guestBook1;
	private MessengerServerInf server;
	private String homepageMember;
	@FXML private TextField text_title;
	@FXML private TextArea text_con;

	public void setServer(MessengerServerInf server) {
		this.server = server;
	}
	
	public void setHomepageMember(String homepageMember) {
		this.homepageMember = homepageMember;
	}
	
	public void setgController(GuestBookController parentController) {
		this.parentController = parentController;
	
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setOnclickSave() throws RemoteException{
		String title = text_title.getText();
		String content = text_con.getText();
		HpVO vo = new HpVO();
		vo.setHpMem(homepageMember);
		List<HpVO> list = server.selectHp(vo);
		vo = list.get(0);
		GbkVO vo2 = new GbkVO();
		vo2.setGbkPtkid("YES");
		vo2.setGbkMem(homepageMember);
		vo2.setGbkWrimem(UserVO.getInstance().getId());
		vo2.setGbkCon(content);
		vo2.setGbkHp(vo.getHpCd());
		vo2.setGbkTit(title);
		server.insert(vo2);
		parentController.setTableDateList();
		setOnclickGuestBookCancel(null);
		
	}
	
	public void setOnclickGuestBookCancel(ActionEvent event) {
		main_pane = parentController.getPane_main();
		guestBook1 = parentController.getGuest_main();
		main_pane.getChildren().removeAll(main_pane.getChildren());
		main_pane.getChildren().add(guestBook1);
		
	}

}
