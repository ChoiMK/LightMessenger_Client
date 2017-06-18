package com.light.hompage.client;

import java.io.File;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import com.light.client.file.TCPFileClient;
import com.light.messenger.MessengerServerInf;
import com.light.vo.ImgVO;

public class UpdatePhotoController implements Initializable{
	
	@FXML TextField text_title;
	@FXML TextArea text_content;
	@FXML ImageView image_main;
	private ImgVO vo = new ImgVO();
	private MessengerServerInf server;
	private PhotoController3 photoController3;
	File file;
	
	public void setVo(ImgVO vo) {
		this.vo = vo;
	}
	
	public void setServer(MessengerServerInf server) {
		this.server = server;
	}

	public void setPhotoController3(PhotoController3 photoController3) {
		this.photoController3 = photoController3;
	}



	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	public void setData(){
		text_title.setText(vo.getImgfraTit());
		text_content.setText(vo.getImgfraCon());
		Image img = new Image("file:/"+vo.getImgfraImg());
		image_main.setImage(img);
	}
	@FXML
	public void cancle(ActionEvent event){
		Stage stage = (Stage) image_main.getScene().getWindow();
		stage.close();
	}
	@FXML
	public void update(ActionEvent event) throws RemoteException{
		vo.setImgfraTit(text_title.getText());
		vo.setImgfraCon(text_content.getText());
		if(!(file == null)){
			TCPFileClient TCPC = new TCPFileClient();
	    	Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						server.fileUpdate(vo.getImgfraNum());
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					
				}
			});
	    	thread.start();
	    	TCPC.clientRun(file.getPath());
			}
    	photoController3.setData();
		Stage stage = (Stage) image_main.getScene().getWindow();
		stage.close();
	}
	@FXML
	public void selectFile (ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        fileChooser.getExtensionFilters().add(extFilter);
       
        file = fileChooser.showOpenDialog(null);
      
        Image img = new Image("file:/"+file.getPath());
        image_main.setImage(img);
		
		
	}

}
