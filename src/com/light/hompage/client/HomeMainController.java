package com.light.hompage.client;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.LoaderHandler;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.UserController;

import com.light.client.file.ImageReceiveClient;
import com.light.messenger.MessengerServerInf;
import com.light.vo.FdVO;
import com.light.vo.HpVO;
import com.light.vo.MemVO;
import com.light.vo.NumVO;
import com.light.vo.UserVO;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class HomeMainController implements Initializable{
	
	private MessengerServerInf server;
	private String homepageMember;
	private String color;
	@FXML private ComboBox com_miniHome;
	@FXML private Label label_state;
	@FXML private Label label_main;
	@FXML private BorderPane boarderPane_main;
	@FXML private ImageView image_pro;
	
	public String getHomepageMember() {
		return homepageMember;
	}
	public void setHomepageMember(String homepageMember){
		this.homepageMember = homepageMember;
		
	
	}
	public void setServer(MessengerServerInf server) throws RemoteException {
		this.server = server;
		hompageDataSet();
		comboboxSet();
		comboboxSelect();
		try {
			onclickLabel(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void comboboxSelect(){
		com_miniHome.valueProperty().addListener(new ChangeListener<String>(){
			@Override
			public void changed(ObservableValue<? extends String> arg0,
					String arg1, String arg2) {
				if(arg2.equals("내미니홈피가기")){
					homepageMember = UserVO.getInstance().getId();
				}else{
					homepageMember = arg2;
					}
					try {
						hompageDataSet();
						onclickLabel(null);
					}catch (IOException e) {
						e.printStackTrace();
					}
				
			}});
	}
	public void comboboxSet() throws RemoteException{
		
		List<FdVO> list = server.getUserList(UserVO.getInstance().getId());
		com_miniHome.getItems().addAll(FXCollections.observableArrayList("내미니홈피가기"));
		for(int i=0; i<list.size();i++){
		if(i==0){
			
			com_miniHome.getItems().addAll(FXCollections.observableArrayList(list.get(i).getFdLt()));
		}else{
			com_miniHome.getItems().addAll(FXCollections.observableArrayList(list.get(i).getFdLt()));
			}
		}

	}
	public void hompageDataSet() throws RemoteException{
		int rnd = (int)(Math.random()*(9999-1000+1))+1;
		HpVO vo = new HpVO();
		vo.setHpMem(homepageMember);
		List<HpVO> list = server.selectHp(vo);
		vo = list.get(0);

		label_state.setText(vo.getHpSttwrt());
		label_main.setText(vo.getHpMawrt());
		//홈페이지 색상바꾸기
		boarderPane_main.styleProperty().setValue("-fx-background-color: " + vo.getHpClo());
	    color = vo.getHpClo();
		color = color.substring(1,7);
	    int abc = Integer.parseInt(color, 16);
	    if(abc>8388607){
	    	color = "#000000";
	    }else{
	    	color = "#ffffff";
	    }
		label_state.setTextFill(Color.web(color));
		label_main.setTextFill(Color.web(color));
		MemVO vo2 = new MemVO();
		vo2.setMemId(vo.getHpMem());
		List<MemVO> list2 = server.selectmemList(vo2);
		vo2 = list2.get(0);
		if(!(vo2.getMemImg() ==null)){
		server.sendImg(vo2.getMemImg(),rnd);
		ImageReceiveClient imgrc = new ImageReceiveClient();
		image_pro.setImage(imgrc.clientRun(rnd));
	    image_pro.visibleProperty().set(true);
		}else{
			image_pro.visibleProperty().set(false);
		}
	};
	
	@FXML 
	private Pane pane_main;
	
	private Pane guestBook1;
	
	public Pane getGuestBook1() {
		return guestBook1;
	}
	
	private Pane diary1;
	
	public Pane getDiary1() {
		return diary1;
	}
	private Pane phto1;
	
	public Pane getPhto1() {
		return phto1;
	}
	private Pane profile;
	
	private Pane set;
	
	@FXML
	private Pane label;
	
	private Image image;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
	}
	
	public void setMainDate(){
		
	}
	public Pane getPane_main() {
		return pane_main;
	}
	@FXML
	public void onclickDiary(ActionEvent event) throws IOException {
		FXMLLoader loader_diary1 = new FXMLLoader(getClass().getResource("Diary1.fxml"));
		diary1 = (Pane)loader_diary1.load();
		DiaryController diaryController = loader_diary1.getController();
		diaryController.setController(this);
		diaryController.setPane_main(pane_main);
		diaryController.setServer(server);
		diaryController.setHomepageMember(homepageMember);
		diaryController.setTableDateList();
		pane_main.getChildren().removeAll(pane_main.getChildren());
		pane_main.getChildren().add(diary1);
	}
	@FXML
	public void onclickPhto(ActionEvent event) throws IOException {
		FXMLLoader loader_phto1 = new FXMLLoader(getClass().getResource("Phto1.fxml"));			
		phto1 = (Pane)loader_phto1.load();
		PhtoConutroller phtoController = loader_phto1.getController();
		phtoController.setController(this);
		phtoController.setPane_main(pane_main);
		phtoController.setServer(server);
		phtoController.setHomepageMember(homepageMember);
//		phtoController.setData();
		pane_main.getChildren().removeAll(pane_main.getChildren());
		pane_main.getChildren().add(phto1);
		
	}
	@FXML
	public void onclickGuestbook(ActionEvent event) throws IOException {
		FXMLLoader loader_guestBook1 = new FXMLLoader(getClass().getResource("GuestBook1.fxml"));
		guestBook1 = (Pane)loader_guestBook1.load();
		GuestBookController guestBookController = loader_guestBook1.getController();
		guestBookController.setController(this);
		guestBookController.setPane_main(pane_main);
		guestBookController.setServer(server);
		guestBookController.setHomepageMember(homepageMember);
		guestBookController.setTableDateList();
		pane_main.getChildren().removeAll(pane_main.getChildren());
		pane_main.getChildren().add(guestBook1);
		
	}
	@FXML
	public void onclickProfile(ActionEvent event) throws IOException {
		FXMLLoader loader_profile = new FXMLLoader(getClass().getResource("Profile.fxml"));
		profile = (Pane)loader_profile.load();
		ProfileController profileController = (ProfileController)loader_profile.getController();
		profileController.setServer(server);
		profileController.setHomepageMember(homepageMember);	
		profileController.setColor(color);
		profileController.setData();
		pane_main.getChildren().removeAll(pane_main.getChildren());
		pane_main.getChildren().add(profile);
		
		
		
	}
	@FXML
	public void onclickSet(ActionEvent event) throws IOException {
		
		if(UserVO.getInstance().getId().equals(homepageMember)){
		//설정
		FXMLLoader loader_set = new FXMLLoader(getClass().getResource("Set.fxml"));
		set = (Pane)loader_set.load();
		SetController setcontroller = (SetController)loader_set.getController();
		setcontroller.setServer(server);
		setcontroller.setHomeMainController(this);
		setcontroller.setHomepageMember(homepageMember);
		setcontroller.setData();
		pane_main.getChildren().removeAll(pane_main.getChildren());
		pane_main.getChildren().add(set);
		}else{
			JOptionPane.showMessageDialog(null, "자신의 홈페이지만 이용할 수 있습니다.");
		}
		
	}
	@FXML
	public void onclickLabel(MouseEvent event) throws IOException {
		FXMLLoader loader_label = new FXMLLoader(getClass().getResource("AAAback.fxml"));
		label = (Pane)loader_label.load();
		HomepageImageController imageController = loader_label.getController();
		imageController.setServer(server);
		imageController.setHomepageMember(homepageMember);
		imageController.setImage();
		pane_main.getChildren().removeAll(pane_main.getChildren());
		pane_main.getChildren().add(label);
	}
}
