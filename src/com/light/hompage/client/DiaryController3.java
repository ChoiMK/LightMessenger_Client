package com.light.hompage.client;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.light.messenger.MessengerServerInf;
import com.light.vo.DiaVO;
import com.light.vo.DiaVO;
import com.light.vo.UserVO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DiaryController3 implements Initializable{

	//@FXML private Label l
		@FXML private TextField textfield_tit;//글제목
		@FXML private Label label_name;//글쓴이
		@FXML private Label label_date;//날짜
		@FXML private TextArea text_fild1;//내용
		@FXML private ListView listView_dek;//댓글내용
		@FXML private TextField text_aa;//댓글등록?
		@FXML private Button button;
		private MessengerServerInf server;
		private String homepageMember;

		public void setServer(MessengerServerInf server) {
			this.server = server;
		}

		public void setHomepageMember(String homepageMember) {
			this.homepageMember = homepageMember;
		}

		private DiaryController parentController;
		private DiaVO nowvo = new DiaVO();
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			
		}
		
		public void setParentController(DiaryController parentController) {
			this.parentController = parentController;
		}

		public void setDiaryDate(DiaVO vo) throws IOException {

			textfield_tit.setText(vo.getDiaTit());
			label_name.setText(vo.getDiaWrimem());
			label_date.setText(vo.getDiaWridt());
			text_fild1.setText(vo.getDiaCon());
			nowvo.setDiaCon(vo.getDiaCon());
			nowvo.setDiaHp(vo.getDiaHp());
			nowvo.setDiaMem(vo.getDiaMem());
			nowvo.setDiaNum(vo.getDiaNum());
			nowvo.setDiaPtkid(vo.getDiaPtkid());
			nowvo.setDiaTit(vo.getDiaTit());
			nowvo.setDiaWridt(vo.getDiaWridt());
			nowvo.setDiaWrimem(vo.getDiaWrimem());
			setComment();
			if(UserVO.getInstance().getId().equals(homepageMember)){
				textfield_tit.setEditable(true);
				text_fild1.setEditable(true);
			}else{
				textfield_tit.setEditable(false);
				text_fild1.setEditable(false);
				
			}
		}
		public void setComment() throws IOException{
			DiaVO vo2 = new DiaVO();
			vo2.setDiaPtkid(nowvo.getDiaNum());
			List<DiaVO> list = server.setTabledata(vo2);
			
			listView_dek.getItems().clear();
			for(int i = 0 ; i< list.size(); i++){
			
				FXMLLoader loader = new FXMLLoader(getClass().getResource("HomepageComment.fxml"));
				AnchorPane pane = (AnchorPane)loader.load();
				HomepageCommentController commentController = loader.getController();
				vo2 = list.get(i);
				String id = vo2.getDiaWrimem();
				String con = vo2.getDiaCon();
				String code = vo2.getDiaNum();
				String date = vo2.getDiaWridt();
				commentController.setData(id, con, code, date);
				commentController.setServer(server);
				commentController.setHomepageMember(homepageMember);
				commentController.setParentController2(DiaryController3.this);
				commentController.setSelect(2);
				listView_dek.getItems().add(pane);
				}
		}
		
		public void update(ActionEvent event) throws RemoteException {
			if(UserVO.getInstance().getId().equals(homepageMember)){
				nowvo.setDiaTit(textfield_tit.getText());  
				nowvo.setDiaCon(text_fild1.getText());
				server.update(nowvo);
				parentController.setTableDateList();
				
				Stage stage = (Stage)button.getScene().getWindow();
				stage.close();
			}else{
				JOptionPane.showMessageDialog(null, "자신의 게시물만 수정 할 수 있습니다.");				
			}
		}
		public void delete(ActionEvent event) throws RemoteException {
			if(UserVO.getInstance().getId().equals(homepageMember)){
				server.deleteDia(nowvo.getDiaNum());
				parentController.setTableDateList();
				Stage stage = (Stage)button.getScene().getWindow();
				stage.close();
			}else{
				JOptionPane.showMessageDialog(null, "자신의 게시물만 삭제 할 수 있습니다.");
				
			}
		}
		public void insertComent(ActionEvent event) throws IOException{
			if(!text_aa.getText().isEmpty()){
			DiaVO vo3 = new DiaVO();
			vo3.setDiaPtkid(nowvo.getDiaNum());
			vo3.setDiaHp(nowvo.getDiaHp());
			vo3.setDiaCon(text_aa.getText());
			vo3.setDiaWrimem(UserVO.getInstance().getId());
			vo3.setDiaMem(homepageMember);
			server.insert(vo3);
			setComment();
			text_aa.clear();
			}else{
				JOptionPane.showMessageDialog(null, "내용을 입력하세요.");
			}
		}
		public void cancle(ActionEvent event) throws RemoteException{
			parentController.setTableDateList();
			Stage stage = (Stage)button.getScene().getWindow();
			stage.close();
		}

}
