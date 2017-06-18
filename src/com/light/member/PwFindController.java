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
				JOptionPane.showMessageDialog(null, "��ġ���� �ʽ��ϴ�.");
			}else{
			Mail mail = new Mail();
			mail.setResive(email);
			mail.setTitle(id +"ȸ������ ��й�ȣ �Դϴ�.");
			mail.setContent(id +"ȸ������ ��й�ȣ : [" + pw +"]");
			try {
				mail.send();
				JOptionPane.showMessageDialog(null, "������ �߼۵Ǿ����ϴ�.");
				System.out.println(pw);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "��ȿ���� �ʴ� �̸����Դϴ�.");
				e.printStackTrace();
			}
			}
		}else{
			JOptionPane.showMessageDialog(null, "������ �Է��ϼ���");
		}
			
		
		
	}
	public void cancle(){
		Scene scene = t_id.getScene();
		Stage stage = (Stage) scene.getWindow();
		stage.close();
		JOptionPane.showMessageDialog(null, "����մϴ�.");
		
	}

}
