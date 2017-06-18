package com.light.manager.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.light.messenger.MessengerServerInf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ManngerMainController implements Initializable{
	@FXML private Pane pane_center;
	
	@FXML private BorderPane borderPane_center1;
	@FXML private BorderPane borderPane_boardMain;
	private static int buttonCheak;
	private MessengerServerInf server;
	
	public void setServer(MessengerServerInf server) {
		this.server = server;
	}
	public static int getButtonCheak() {
		return buttonCheak;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
			
	}
	
	public void member(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Member.fxml"));
		borderPane_center1 = (BorderPane)loader.load();
		memberController memberController = (memberController)loader.getController();
		memberController.setServer(server);
		pane_center.getChildren().removeAll(pane_center.getChildren());
		pane_center.getChildren().addAll(borderPane_center1);
		
	}
	public void imageBorad(ActionEvent event) throws IOException{
		buttonCheak = 1;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BoardMain.fxml"));
		borderPane_boardMain = (BorderPane)loader.load();
		BoardController boardController = (BoardController)loader.getController();
		boardController.setServer(server);
		boardController.memSetTableData();
		pane_center.getChildren().removeAll(pane_center.getChildren());
		pane_center.getChildren().addAll(borderPane_boardMain);
		
		
	}
	public void diaryBoard(ActionEvent event) throws IOException{
		buttonCheak = 2;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BoardMain.fxml"));
		borderPane_boardMain = (BorderPane)loader.load();
		BoardController boardController = (BoardController)loader.getController();
		boardController.setServer(server);
		boardController.memSetTableData();
		pane_center.getChildren().removeAll(pane_center.getChildren());
		pane_center.getChildren().addAll(borderPane_boardMain);
		
	}
	public void visitorBoard(ActionEvent event) throws IOException{
		buttonCheak = 3;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BoardMain.fxml"));
		borderPane_boardMain = (BorderPane)loader.load();
		BoardController boardController = (BoardController)loader.getController();
		boardController.setServer(server);
		boardController.memSetTableData();
		pane_center.getChildren().removeAll(pane_center.getChildren());
		pane_center.getChildren().addAll(borderPane_boardMain);
		
	}
}