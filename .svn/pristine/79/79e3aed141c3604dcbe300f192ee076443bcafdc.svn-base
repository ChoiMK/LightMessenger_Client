package com.light.member;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.light.mail.Mail;
import com.light.messenger.MessengerServerInf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PwFindController implements Initializable {
	
	@FXML private TextField t_id;
	@FXML private TextField t_name;
	@FXML private TextField t_mail;
	private MessengerServerInf server;
	
	public void setServer(MessengerServerInf server) {
		this.server = server;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
	}
	
	public void pwFind(ActionEvent event) throws RemoteException{
		if(!t_id.getText().isEmpty()&&!t_name.getText().isEmpty()&&!t_mail.getText().isEmpty()){
			String email = t_mail.getText();
			String name = t_name.getText();
			String id = t_id.getText();
			String pw = server.pwReturn(email, name, id);
			if(pw.isEmpty()){
				JOptionPane.showMessageDialog(null, "일치하지 않습니다.");
			}else{
			Mail mail = new Mail();
			mail.setResive(email);
			mail.setTitle(id +"회원님의 비밀번호 입니다.");
			mail.setContent(id +"회원님의 비밀번호 : [" + pw +"]");
			try {
				mail.send();
				JOptionPane.showMessageDialog(null, "메일이 발송되었습니다.");
				System.out.println(pw);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "유효하지 않는 이메일입니다.");
				e.printStackTrace();
			}
			}
		}else{
			JOptionPane.showMessageDialog(null, "정보를 입력하세요");
		}
			
		
		
	}
	public void cancle(){
		Scene scene = t_id.getScene();
		Stage stage = (Stage) scene.getWindow();
		stage.close();
		JOptionPane.showMessageDialog(null, "취소합니다.");
		
	}

}
