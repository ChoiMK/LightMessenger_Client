package com.light.hompage.client;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import com.light.messenger.MessengerServerInf;
import com.light.vo.DiaVO;
import com.light.vo.GbkVO;
import com.light.vo.ImgVO;
import com.light.vo.UserVO;

public class HomepageCommentController implements Initializable {
	@FXML private Label label_id;
	@FXML private Label label_con;
	@FXML private Label label_code;
	@FXML private Label label_date;
	@FXML private TextField text_con;
	@FXML private Button button_delete;
	@FXML private Button button_update;
	private MessengerServerInf server;
	private String homepageMember;
	private int select;
	public void setSelect(int select) {
		this.select = select;
	}

	private DetaillController parentController1;
	private DiaryController3 parentController2;
	private PhotoController3 parentController3;
	
	public PhotoController3 getParController3() {
		return parentController3;
	}
	public void setParController3(PhotoController3 parController3) {
		this.parentController3 = parController3;
	}

	public void setParentConreoller(DetaillController parentConreoller) {
		this.parentController1 = parentConreoller;
	}
	public void setParentController2(DiaryController3 parentController2) {
		this.parentController2 = parentController2;
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
	public void setData(String id, String con, String code, String date){
		label_id.setText(id);
		label_con.setText(con);
		label_code.setText(code);
		label_date.setText(date);
		text_con.setText(con);
		if(!(label_id.getText().equals(UserVO.getInstance().getId()))){
			button_delete.setVisible(false);
			button_update.setVisible(false);
		}
	}
	
	@FXML
	public void update(ActionEvent event) throws IOException{
			if(label_con.visibleProperty().get()){
				label_con.visibleProperty().set(false);
				text_con.visibleProperty().set(true);
			}else{
				switch (select) {
					case 1:
						ImgVO vo2 = new ImgVO();
						vo2.setImgfraNum(label_code.getText());
						List<ImgVO> list2  = server.setTabledata(vo2);
						vo2=list2.get(0);
						vo2.setImgfraCon(text_con.getText());
						server.update(vo2);
						label_con.visibleProperty().set(true);
						text_con.visibleProperty().set(false);
						parentController3.setComment();
						break;
						
					case 2:
						DiaVO vo = new DiaVO();
						vo.setDiaNum(label_code.getText());
						List<DiaVO> list  = server.setTabledata(vo);
						vo=list.get(0);
						vo.setDiaCon(text_con.getText());
						server.update(vo);
						label_con.visibleProperty().set(true);
						text_con.visibleProperty().set(false);
						parentController2.setComment();
						break;
					case 3:
						GbkVO vo3 = new GbkVO();
						vo3.setGbkNum(label_code.getText());
						List<GbkVO> list3  = server.setTabledata(vo3);
						vo3=list3.get(0);
						vo3.setGbkCon(text_con.getText());
						server.update(vo3);
						label_con.visibleProperty().set(true);
						text_con.visibleProperty().set(false);
						parentController1.setComment();
						break;
				}
			
		}
		
	}
	
	@FXML
	public void delete(ActionEvent event) throws IOException{
		switch (select) {
			case 1:
				server.deleteimg(label_code.getText());
				parentController3.setComment();
				break;
			case 2:
				server.deleteDia(label_code.getText());
				parentController2.setComment();
				break;
			case 3:
				server.deletegbk(label_code.getText());
				parentController1.setComment();
				break;
		}
	}

}
