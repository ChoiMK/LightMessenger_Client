package com.light.member;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

import com.light.mail.Mail;
import com.light.messenger.MessengerServerInf;

public class IdFindControll implements Initializable{
	
	@FXML private TextField t_nm;
	@FXML private TextField t_email;
	private MessengerServerInf server;
	
	public void setServer(MessengerServerInf server) {
		this.server = server;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
	}
	
	public void emailSend() throws RemoteException {
		if(!t_nm.getText().isEmpty()&&!t_email.getText().isEmpty()){
		String email = t_email.getText();
		String name = t_nm.getText();
		String id = server.idReturn(email, name);
		if(id.isEmpty()){
			JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다.");
		}else{
			
			Mail mail = new Mail();
			mail.setResive(email);
			mail.setTitle("가입하신 id 입니다.");
			mail.setContent("가입하신 id : [" + id +"]");
			try {
				mail.send();
				JOptionPane.showMessageDialog(null, "이메일 전송완료");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "이메일이 유효하지 않습니다.");
				e.printStackTrace();
			}
		}
			
		}else{
			JOptionPane.showMessageDialog(null, "정보를 입력하세요.");
		}
	
	}	

	public void cancle(){
		Scene scene = t_nm.getScene();
		Stage stage = (Stage) scene.getWindow();
		stage.close();
		JOptionPane.showMessageDialog(null, "취소합니다.");
		
	}
}
