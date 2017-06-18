package com.light.messenger.client;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.StrokeTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.JOptionPane;

import application.MyController;

import com.light.client.file.ImageReceiveClient;
import com.light.client.file.TCPFileClient;
import com.light.messenger.MessengerClientInf;
import com.light.messenger.MessengerServerInf;
import com.light.vo.MemVO;
import com.light.vo.UserVO;

public class ProfileSettingController implements Initializable{
	@FXML private PasswordField text_pw;
	@FXML private PasswordField text_pwC;
	@FXML private TextField text_nic;
	@FXML private TextField text_name;
	@FXML private TextField text_add;
	@FXML private TextField text_num;
	@FXML private DatePicker date_bir;
	@FXML private ImageView image_pro;
	private MessengerServerInf server;
	private MyController myController;
	
	public void setMyController(MyController myController) {
		this.myController = myController;
	}

	File file;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	public MessengerServerInf getServer() {
		return server;
	}

	public void setServer(MessengerServerInf server) {
		this.server = server;
	}
	
	public void setData() throws RemoteException{
		int rnd = (int)(Math.random()*(9999-1000+1))+1;
		MemVO vo = new MemVO();
		vo.setMemId(UserVO.getInstance().getId());
		List<MemVO> list = server.selectmemList(vo);
		vo = list.get(0);
		text_pw.setText(vo.getMemPw());
		text_pwC.setText(vo.getMemPw());
		text_nic.setText(vo.getMemNic());
		text_name.setText(vo.getMemNm());
		text_add.setText(vo.getMemAdd());
		text_num.setText(vo.getMemPhonum());
		String date = vo.getMemBir();
		int year = Integer.parseInt(date.substring(0,4));
		int month= Integer.parseInt(date.substring(5,7));
		int day= Integer.parseInt(date.substring(8,10)); 
		date_bir.setValue(LocalDate.of(year, month, day));
		if(vo.getMemImg() != null ){
			server.sendImg(vo.getMemImg(),rnd);
			ImageReceiveClient imgrc = new ImageReceiveClient();
			image_pro.setImage(imgrc.clientRun(rnd));
			
		}
	}
	public void update(ActionEvent event) throws RemoteException{
		MemVO vo = new MemVO();
		vo.setMemId(UserVO.getInstance().getId());
		List<MemVO> list = server.selectmemList(vo);
		vo = list.get(0);
		vo.setMemPw(text_pw.getText());
		vo.setMemBir(date_bir.getValue().toString());
		vo.setMemNic(text_nic.getText());
		vo.setMemNm(text_name.getText());
		vo.setMemPhonum(text_num.getText());
		vo.setMemAdd(text_add.getText());
		server.update(vo);
		if(!(file == null)){
		TCPFileClient TCPC = new TCPFileClient();
    	Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					server.fileset(UserVO.getInstance().getId());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				
			}
		});
    	thread.start();
    	TCPC.clientRun(file.getPath());
		}
		cancle(null);
		myController.setImage();
		
		
		
		
	}
	public void cancle(ActionEvent event){
		Stage stage = (Stage) text_pw.getScene().getWindow();
		stage.close();
		
	}
	public void nicCheak(ActionEvent event) throws RemoteException{
		boolean nic = server.nicCheak(text_nic.getText());
		if(nic){
			JOptionPane.showMessageDialog(null, "닉네임 사용가능합니다.");
		}else{
			JOptionPane.showMessageDialog(null, "닉네임 중복입니다.");
		}
	}
	
	public void testimg (ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        fileChooser.getExtensionFilters().add(extFilter);
       
        //Show open file dialog
        file = fileChooser.showOpenDialog(null);
      
        if(file!=null){
        Image img = new Image("file:/"+file.getPath());
        image_pro.setImage(img);
        }
		
		
	}
	
	@FXML
	public void withdrawal(ActionEvent event) throws IOException{
		int result = JOptionPane.showConfirmDialog(null, "당신은 혼자가 아닙니다!! 탈퇴하시겠습니까?","회원탈퇴",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		result = JOptionPane.showConfirmDialog(null, "진심으로 탈퇴하시겠습니까?","회원탈퇴",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if(result == 0){
			MemVO vo = new MemVO();
			vo.setMemId(UserVO.getInstance().getId());
			List<MemVO> list = server.selectmemList(vo);
			vo = list.get(0);
			vo.setMemWrlwtr("NO");
			server.update(vo);
			String userId = UserVO.getInstance().getId();
			server.userlogOut(userId);
			Stage stage = (Stage) image_pro.getScene().getWindow();
			stage.close();
			myController.close();
			
			GridPane root2=new GridPane();
			Text text=new Text("LIGHT");
			text.setFill(Color.TRANSPARENT);
			text.setStrokeWidth(3);
			text.setFont(Font.font("Impact",80)); 
			text.layoutXProperty();
			text.autosize();
			root2.setPadding(new Insets(100, 100, 100, 120));
			
			StrokeTransition text_stroke_trans = new StrokeTransition(Duration.millis(300),text, Color.GOLD,   Color.LIGHTGREEN);
			text_stroke_trans.setDelay(Duration.millis(10));
			text_stroke_trans.setCycleCount(Timeline.INDEFINITE); 
			text_stroke_trans.setAutoReverse(true);
			
			text_stroke_trans.play();
			root2.getChildren().addAll(text);
			
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../../application/Login.fxml"));
			BorderPane pane =(BorderPane)loader.load();
			Scene scene = new Scene(pane,390,690);
			pane.getChildren().add(root2);
			pane.getStylesheets().add("/com/light/view/styles/Messenger_style.css");
			Stage newStage = new Stage();
			newStage.setScene(scene);
			newStage.show();
			
			ArrayList<MessengerClientInf> clientList= server.clientList(UserVO.getInstance().getId());
			
			
			if(clientList.size()>0){
				for(int j = 0; j<clientList.size();j++){
					clientList.get(j).getUserList();
				}
			}
			
		}
		
	}

}
