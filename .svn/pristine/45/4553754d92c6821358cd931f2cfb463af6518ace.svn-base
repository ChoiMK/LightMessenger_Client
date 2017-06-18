package com.light.hompage.client;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javax.swing.JOptionPane;

import com.light.messenger.MessengerServerInf;
import com.light.vo.ImgVO;
import com.light.vo.UserVO;

public class PhtoConutroller implements Initializable{

	private Pane pane_main;
	private Pane phto_main;
	private MessengerServerInf server;
	private String homepageMember;
	@FXML private GridPane gridpane_photo;
	private int num = 0;
	private int counter;

	public void setServer(MessengerServerInf server) {
		this.server = server;
	}
	
	public void setHomepageMember(String homepageMember) {
		this.homepageMember = homepageMember;
		try {
			setData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Pane getPane_main() {
		return pane_main;
	}

	public Pane getPhto_main() {
		return phto_main;
	}

	private Pane phto2; 
	HomeMainController parentController = null;
	
	public void setPane_main(Pane pane_main) {
		this.pane_main = pane_main;
	}
	public void setController(HomeMainController parentController) {
		this.parentController = parentController;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void onclickPhto(ActionEvent event) throws IOException {
		if(UserVO.getInstance().getId().equals(homepageMember)){
			FXMLLoader phto_loader = new FXMLLoader(getClass().getResource("Phto2.fxml"));
			phto2 = (Pane)phto_loader.load();
			PhtoConutroller2 phtoconutroller2 = phto_loader.getController();
			phtoconutroller2.setgController(this);
			phtoconutroller2.setHomepageMember(homepageMember);
			phtoconutroller2.setServer(server);
			phto_main = parentController.getPhto1();
			pane_main.getChildren().removeAll(pane_main.getChildren());
			pane_main.getChildren().add(phto2);
		}else{
			JOptionPane.showMessageDialog(null, "자신의 홈페이지에서만 글쓰기가 가능합니다.");
		}
	}
	public void setData() throws IOException{
		gridpane_photo.getChildren().removeAll(gridpane_photo.getChildren());
		ImgVO vo = new ImgVO();
		vo.setImgfraMem(homepageMember);
		vo.setImgfraPtkid("YES");
		List<ImgVO> list = server.setTabledata(vo);
		if(list.size()>0){
		counter = list.size();
		int count;
		
		count = num;
		
		Data :for(int i =0; i<2;i++){
			for(int j=0; j<3; j++){
				vo = list.get(count);
				FXMLLoader loder = new FXMLLoader(getClass().getResource("Photo.fxml"));
				AnchorPane pane = (AnchorPane) loder.load();
				PhotoController4 photoController = loder.getController();
				photoController.setHomepageMember(homepageMember);
				photoController.setServer(server);
				photoController.setVo(vo);
				photoController.setData();
				photoController.setPhoConutroller(this);
				gridpane_photo.add(pane, j, i);
				count++;
				if(count==list.size()){
					break Data;
				}
			}
			
		}
		}
	}
	@FXML
	public void nextList(ActionEvent event) throws IOException{
		num+=6;
		if(counter<=num){
			JOptionPane.showMessageDialog(null, "마지막 페이이지 입니다.");	
		num -=6;
		}else{
		setData();
		}
	}
	@FXML
	public void homeList(ActionEvent event) throws IOException{
		num = 0;
		setData();
		
	}
	@FXML
	public void beginList(ActionEvent event) throws IOException{
		if(!(num == 0)){
		num -=6;
		setData();
		}else{
			JOptionPane.showMessageDialog(null, "처음 페이지입니다.");
		}
	}

}
