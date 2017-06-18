package com.light.hompage.client;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.light.client.file.ImageReceiveClient;
import com.light.manager.client.BoardController;
import com.light.messenger.MessengerServerInf;
import com.light.vo.ImgVO;
import com.light.vo.UserVO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PhotoController3 implements Initializable {
	
	@FXML private Label label_num;
	@FXML private Label label_title;
	@FXML private Label label_writer;
	@FXML private Label label_date;
	@FXML private Label label_con;
	@FXML private ImageView image_content;
	@FXML private TextArea text_aa;
	@FXML private ListView listView_dek;
	
	private ImgVO vo = new ImgVO();
	private MessengerServerInf server;
	private String homepageMember;
	private PhotoController4 phoConutroller4;
	private PhtoConutroller photoController;
	
	
	
	public void setPhotoController(PhtoConutroller photoController) {
		this.photoController = photoController;
	}

	public void setPhoConutroller4(PhotoController4 phoConutroller4) {
		this.phoConutroller4 = phoConutroller4;
	}

	public void setVo(ImgVO vo) {
		this.vo = vo;
	}

	public void setServer(MessengerServerInf server) {
		this.server = server;
	}

	public void setHomepageMember(String homepageMember) {
		this.homepageMember = homepageMember;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setData() throws RemoteException{
		int rnd = (int)(Math.random()*(9999-1000+1))+1;
		label_num.setText(vo.getImgfraRownum());
		label_title.setText(vo.getImgfraTit());
		label_writer.setText(vo.getImgfraWrimem());
		label_date.setText(vo.getImgfraDt());
		label_con.setText(vo.getImgfraCon());
		server.sendImg(vo.getImgfraImg(),rnd);
		ImageReceiveClient imgrc = new ImageReceiveClient();
		image_content.setImage(imgrc.clientRun(rnd));
	}
	public void setComment() throws IOException{
		ImgVO vo2 = new ImgVO();
		vo2.setImgfraPtkid(vo.getImgfraNum());
		List<ImgVO> list = server.setTabledata(vo2);
		listView_dek.getItems().clear();
		for(int i = 0 ; i< list.size(); i++){
		
			FXMLLoader loader = new FXMLLoader(getClass().getResource("HomepageComment.fxml"));
			AnchorPane pane = (AnchorPane)loader.load();
			HomepageCommentController commentController = loader.getController();
			
			vo2 = list.get(i);
			String id = vo2.getImgfraWrimem();
			String con = vo2.getImgfraCon();
			String code = vo2.getImgfraNum();
			String date = vo2.getImgfraDt();
			commentController.setData(id, con, code, date);
			commentController.setServer(server);
			commentController.setHomepageMember(homepageMember);
			commentController.setSelect(1);
			commentController.setParController3(this);
			listView_dek.getItems().add(pane);
			}
	}
	public void insertComent(ActionEvent event) throws IOException{
		if(!text_aa.getText().isEmpty()){
		ImgVO vo3 = new ImgVO();
		vo3.setImgfraPtkid(vo.getImgfraNum());
		vo3.setImgfraHp(vo.getImgfraHp());
		vo3.setImgfraCon(text_aa.getText());
		vo3.setImgfraWrimem(UserVO.getInstance().getId());
		vo3.setImgfraMem(homepageMember);
		server.insert(vo3);
		setComment();
		text_aa.clear();
		}else{
			JOptionPane.showMessageDialog(null, "내용을 입력해요");
		}
	}
	@FXML
	public void update(ActionEvent event) throws IOException{
		if(UserVO.getInstance().getId().equals(homepageMember)){
			FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdatePhoto.fxml"));
			BorderPane pane = (BorderPane)loader.load();
			Scene scene = new Scene(pane,700,700);
			UpdatePhotoController updateController = loader.getController();
			updateController.setPhotoController3(this);
			updateController.setServer(server);
			updateController.setVo(vo);
			updateController.setData();
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		}else{
			JOptionPane.showMessageDialog(null, "자신의 게시물만 수정이 가능합니다.");
		}
		
		}
	@FXML
	public void delete(ActionEvent event) throws IOException{
		if(UserVO.getInstance().getId().equals(homepageMember)){
			server.deleteimg(vo.getImgfraNum());
			Stage stage = (Stage) listView_dek.getScene().getWindow();
			stage.close();
			photoController.homeList(null);
		}else{
			JOptionPane.showMessageDialog(null, "자신의 게시물만 삭제가 가능합니다.");
		}
		
	}
	@FXML
	public void cancle(ActionEvent event) throws IOException{
		Stage stage = (Stage) listView_dek.getScene().getWindow();
		stage.close();
		phoConutroller4.setData();
		
	}

}
