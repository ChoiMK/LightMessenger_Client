package com.light.messenger.client;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.StrokeTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import application.MyController;
import application.UserController;

import com.light.hompage.client.HomeMainController;
import com.light.message.client.Client;
import com.light.messenger.MessengerClientInf;
import com.light.messenger.MessengerServerInf;
import com.light.vo.FdVO;
import com.light.vo.UserVO;


public class MessengerController implements Initializable{
	private FXMLLoader loader;
	
	@FXML ListView listview = new ListView<>();
	@FXML TextField search_field = new TextField();
	
	private MessengerServerInf server;
	private Client client;
	
	
	public void setClient(Client client) {
		this.client = client;
	}

	public void setServer(MessengerServerInf server) {
		this.server = server;
	}
	
	//친구검색
	@FXML
	public void userSearch(KeyEvent event){
		if (event.getCode().toString().equals("ENTER")) {
			try {
				
				List<String> userList = server.search(search_field.getText(),UserVO.getInstance().getId());
				
				loader = new FXMLLoader(getClass().getResource("Search.fxml"));
				BorderPane pane = (BorderPane) loader.load();
				
				SearchController searchController = (SearchController)loader.getController();
				
				searchController.setServer(this.server);
				searchController.setUserList(userList);
				searchController.setMessengerController(this);
				
				Scene scene = new Scene(pane, 400, 450);
				Stage stage = new Stage();
				stage.setScene(scene);
				
				stage.initModality(Modality.APPLICATION_MODAL); //부모창 못 닫음
				stage.show();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	
	public void getUserList() {
		
		try {	
				listview.getItems().clear();
				//내꺼리스트
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/My.fxml"));
				AnchorPane my = (AnchorPane) loader.load();
				MyController mycontroller = loader.getController();
				mycontroller.setServer(server);
				listview.getItems().add(my);
				
				List<FdVO> list = new ArrayList<FdVO>();
				String id = UserVO.getInstance().getId();
				list = server.getUserList(id);
				int count = list.size();
				
				//친구 리스트
				for(int i=0; i<count; i++){
					FXMLLoader loader2 = new  FXMLLoader(getClass().getResource("/application/User.fxml"));
					AnchorPane user = (AnchorPane) loader2.load();
					
					user.getStylesheets().add("/com/light/view/styles/Messenger_style.css");
					
					UserController userController = (UserController) loader2.getController();
					userController.setServer(server);
					userController.setClient(client);
					userController.setMessengerController(this);
					userController.getFriendList(i);
					listview.getItems().addAll(user);
				}
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void logOut(MouseEvent event){
		String userId = UserVO.getInstance().getId();
		try {
			server.userlogOut(userId);
			Stage stage = (Stage) search_field.getScene().getWindow();
			stage.close();
			
			GridPane root2=new GridPane();
			Text text=new Text("LIGHT");
		    text.setFill(Color.TRANSPARENT);
		    text.setStrokeWidth(3);
		    text.setFont(Font.font("Impact",80)); //아놔 폰트
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
		
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
