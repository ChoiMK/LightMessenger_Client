package com.light.hompage.client;

import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Time;
import java.util.List;
import java.util.ResourceBundle;

import com.light.client.file.ImageReceiveClient;
import com.light.messenger.MessengerServerInf;
import com.light.vo.ImgVO;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class HomepageImageController implements Initializable {
		
	@FXML Label label_notimage;
	@FXML ImageView image_main;
	private MessengerServerInf server;
	private String homepageMember;
	public MessengerServerInf getServer() {
		return server;
	}

	public void setServer(MessengerServerInf server) {
		this.server = server;
	}

	public String getHomepageMember() {
		return homepageMember;
	}

	public void setHomepageMember(String homepageMember) {
		this.homepageMember = homepageMember;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setImage() throws RemoteException{
		int rnd = (int)(Math.random()*(9999-1000+1))+1;
		ImgVO vo = new ImgVO();
		vo.setImgfraMem(homepageMember);
		vo.setImgfraPtkid("YES");
		List<ImgVO> list = server.setTabledata(vo);
		Timeline timeline = new Timeline();
		if(list.size()!=0){
			if(list.size() == 1){
				vo = list.get(0);
				server.sendImg(vo.getImgfraImg(),rnd);
				ImageReceiveClient imgrc = new ImageReceiveClient();
				image_main.setImage(imgrc.clientRun(rnd));
			}else{
			float count = 0;
			for(int i = 0 ; i<list.size(); i++){
				vo = list.get(i);	
				server.sendImg(vo.getImgfraImg(),rnd);
				ImageReceiveClient imgrc = new ImageReceiveClient();
				KeyValue keyvalue = new KeyValue(image_main.imageProperty(),imgrc.clientRun(rnd));
				KeyFrame keyframe = new KeyFrame(Duration.seconds(count), keyvalue);
				timeline.getKeyFrames().add(keyframe);
				count +=1.5;		
			}
			timeline.play();
			}
			
			
		}else{
			label_notimage.visibleProperty().set(true);
		}
	}

}
