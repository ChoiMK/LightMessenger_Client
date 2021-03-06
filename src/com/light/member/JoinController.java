package com.light.member;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

import com.light.mail.Mail;


import com.light.messenger.MessengerServerInf;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class JoinController implements Initializable{
	
	@FXML private TextField t_id;
	@FXML private TextField t_n;
	@FXML private TextField t_e;
	@FXML private TextField t_ij;
	@FXML private TextField t_nm;
	@FXML private TextField t_add;
	@FXML private TextField t_hp;
	@FXML private DatePicker date_bir;
	@FXML private PasswordField p_pw;
	@FXML private PasswordField p_pws;
	@FXML private boolean cheakId = false;
	@FXML private boolean cheakNic = false;
	@FXML private boolean cheakEmail = false;
	@FXML private boolean cheakIn = false;
	private String emailChek ="";
	private MessengerServerInf server;
		
	public void setServer(MessengerServerInf server) {
			this.server = server;
	}
		
	
	@FXML ComboBox c_gen;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		c_gen.setItems(FXCollections.observableArrayList("남자","여자"));
	}
	
	
	public void NicCheak(ActionEvent event) throws RemoteException{
		String nic = t_n.getText();
		boolean flag = Pattern.matches("^[a-zA-Z0-9가-힣]{3,10}$", nic); 
		if(flag){
		boolean ncCheak = server.nicCheak(nic);
		if(ncCheak){
			JOptionPane.showMessageDialog(null, "닉네임 사용가능합니다.");
			cheakNic = true;
		}else{
			JOptionPane.showMessageDialog(null, "닉네임 중복입니다.");
			cheakNic = false;
		}
		}else{
			JOptionPane.showMessageDialog(null, "양식에 맞지 않은 닉네임입니다.(닉네임은 한글,숫자,영어조합으로 3~10자입니다.)");
			cheakNic = false;
		}

}
	
	
	public void idCheak(ActionEvent event) throws RemoteException{
		String id = t_id.getText();
		boolean flag = Pattern.matches("^[a-zA-Z0-9]{7,14}$", id); 
		
		if(flag){
		boolean result = server.idCheak(id);
		if(result){
			JOptionPane.showMessageDialog(null, "가입가능한 아이디 입니다.");
			//alert("가입가능한 아이디 입니다.", "회원가입");
			cheakId = true;
		}else{
			JOptionPane.showMessageDialog(null, "아이디 중복입니다.");
			//alert("아이디 중복입니다.", "회원가입");
			cheakId = false;
		}

		}else{
			JOptionPane.showMessageDialog(null, "양식에 맞지 않은 아이디입니다.(아이디는 7~14자, 영어,숫자 조합으로 입력하세요)");
			//alert("아이디는 7~14자, 영어,숫자 조합으로 입력하세요", "회원가입");
			cheakId = false;
		}
	}
	
	public String send(){
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 10; i++) {
		    int rIndex = rnd.nextInt(3);
		    switch (rIndex) {
		    case 0:
		        // a-z
		        temp.append((char) ((int) (rnd.nextInt(26)) + 97));
		        break;
		    case 1:
		        // A-Z
		        temp.append((char) ((int) (rnd.nextInt(26)) + 65));
		        break;
		    case 2:
		        // 0-9
		        temp.append((rnd.nextInt(10)));
		        break;
		    }
		}
		String rn = temp.toString();
		
		return rn;
		
	}
    
   
	
	public void emailSend(ActionEvent event){
		String email = t_e.getText();
		String content = send();
		emailChek = content;
		try {
			Mail mail = new Mail();
			mail.setResive(email);
			mail.setTitle("인증번호입니다.");
			mail.setContent("인증번호 : ["+content+"]");
			mail.send();
			
			JOptionPane.showMessageDialog(null, "메일을 발송하였습니다.");
			cheakEmail = true;
			
		} catch (Exception e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "메일이 유효하지 않습니다.");
		}
		
	}
	
	public void emailCheck(){
		String emailij = t_ij.getText();
		if(cheakEmail){
		if(emailij.equals(emailChek)){
			JOptionPane.showMessageDialog(null, "인증번호가 일치합니다.");
			cheakIn = true;
		}else{
			JOptionPane.showMessageDialog(null, "인증번호가 일치하지 않습니다.");
		}
			
		}
	}
	
	public void insertJoin(ActionEvent event) throws RemoteException{
		if(cheakId&&cheakEmail&&cheakNic&&cheakIn){
			if(p_pw.getText().equals(p_pws.getText())){
				if(!t_id.getText().isEmpty() && !p_pw.getText().isEmpty()&& !p_pws.getText().isEmpty() 
						&& ! t_n.getText().isEmpty()&& ! t_e.getText().isEmpty() &&
						!t_nm.getText().isEmpty() && !t_add.getText().isEmpty() &&
						!t_hp.getText().isEmpty()&& (date_bir.getValue() !=null) && !t_ij.getText().isEmpty()){
						String id = t_id.getText();
						String pw = p_pw.getText();
						String nic = t_n.getText();
						String email = t_e.getText();
						String name = t_nm.getText();
						String address = t_add.getText();
						String hp = t_hp.getText();
						String bir = date_bir.getValue().toString();
						String gen = c_gen.getValue().toString();
						server.insertData(id, pw, nic, email, name, address, hp, bir, gen);
						JOptionPane.showMessageDialog(null, "회원가입완료!!!!");
						Scene scene = t_id.getScene();
						Stage stage = (Stage) scene.getWindow();
						stage.close();
					
				}else{
					JOptionPane.showMessageDialog(null, "정보를 모두 입력해주세요.");
				}
				
			}else{
				JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
			}
			
		}else{
			JOptionPane.showMessageDialog(null, "절차를 모두 마쳐주세요.");
		}
		
	}
	
	public void cancle(){
		Scene scene = t_id.getScene();
		Stage stage = (Stage) scene.getWindow();
		stage.close();
		JOptionPane.showMessageDialog(null, "취소합니다.");
		
	}
	
	
	
	
	
	

}
