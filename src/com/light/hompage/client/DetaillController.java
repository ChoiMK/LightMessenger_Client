package com.light.hompage.client;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.light.messenger.MessengerServerInf;
import com.light.vo.GbkVO;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DetaillController implements Initializable{

	//@FXML private Label l
	@FXML private TextField textfield_tit;//������
	@FXML private Label label_name;//�۾���
	@FXML private Label label_date;//��¥
	@FXML private TextArea text_fild1;//����
	@FXML private ListView listView_dek;//��۳���
	@FXML private TextField text_aa;//��۵��?
	@FXML private Button button;
	private MessengerServerInf server;
	private String homepageMember;
	private GuestBookController parentController;
	private GbkVO nowvo = new GbkVO();

	public void setServer(MessengerServerInf server) {
		this.server = server;
	}
	
	public void setHomepageMember(String homepageMember) {
		this.homepageMember = homepageMember;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setParentController(GuestBookController guestBookController) {
		this.parentController = guestBookController;
	}

	public void setGuestBookDate(GbkVO vo) throws IOException {
		textfield_tit.setText(vo.getGbkTit());
		label_name.setText(vo.getGbkWrimem());
		label_date.setText(vo.getGbkWridt());
		text_fild1.setText(vo.getGbkCon());
		nowvo.setGbkCon(vo.getGbkCon());
		nowvo.setGbkHp(vo.getGbkHp());
		nowvo.setGbkMem(vo.getGbkMem());
		nowvo.setGbkNum(vo.getGbkNum());
		nowvo.setGbkPtkid(vo.getGbkPtkid());
		nowvo.setGbkTit(vo.getGbkTit());
		nowvo.setGbkWridt(vo.getGbkWridt());
		nowvo.setGbkWrimem(vo.getGbkWrimem());
		setComment();
		if(UserVO.getInstance().getId().equals(homepageMember)||label_name.getText().equals(UserVO.getInstance().getId())){
			textfield_tit.setEditable(true);
			text_fild1.setEditable(true);
		}else{
			textfield_tit.setEditable(false);
			text_fild1.setEditable(false);
			
		}
	}
	public void setComment() throws IOException{
		GbkVO vo2 = new GbkVO();
		vo2.setGbkPtkid(nowvo.getGbkNum());
		List<GbkVO> list = server.setTabledata(vo2);
		listView_dek.getItems().clear();
		for(int i = 0 ; i< list.size(); i++){
		
			FXMLLoader loader = new FXMLLoader(getClass().getResource("HomepageComment.fxml"));
			AnchorPane pane = (AnchorPane)loader.load();
			HomepageCommentController commentController = loader.getController();
			
			vo2 = list.get(i);
			String id = vo2.getGbkWrimem();
			String con = vo2.getGbkCon();
			String code = vo2.getGbkNum();
			String date = vo2.getGbkWridt();
			commentController.setData(id, con, code, date);
			commentController.setServer(server);
			commentController.setHomepageMember(homepageMember);
			commentController.setSelect(3);
			commentController.setParentConreoller(DetaillController.this);
			listView_dek.getItems().add(pane);
			}
	}
	
	public void update(ActionEvent event) throws RemoteException {
		if(UserVO.getInstance().getId().equals(homepageMember)||label_name.getText().equals(UserVO.getInstance().getId())){
			nowvo.setGbkTit(textfield_tit.getText());  
			nowvo.setGbkCon(text_fild1.getText());
			server.update(nowvo);
			parentController.setTableDateList();
			
			Stage stage = (Stage)button.getScene().getWindow();
			stage.close();
		}else{
			JOptionPane.showMessageDialog(null, "�ڽ��� Ȩ�������� �ڽ��� ���۸� ������ �� �ֽ��ϴ�.");
		}
		
		
	}
	public void delete(ActionEvent event) throws RemoteException {
		if(UserVO.getInstance().getId().equals(homepageMember)||label_name.getText().equals(UserVO.getInstance().getId())){
			
			server.deletegbk(nowvo.getGbkNum());
			parentController.setTableDateList();
			Stage stage = (Stage)button.getScene().getWindow();
			stage.close();
		}else{
			JOptionPane.showMessageDialog(null, "�ڽ��� Ȩ�������� �ڽ��� ���۸� ������ �� �ֽ��ϴ�.");
		}
		
	}
	public void insertComent(ActionEvent event) throws IOException{
		if(!text_aa.getText().isEmpty()){
		GbkVO vo3 = new GbkVO();
		vo3.setGbkPtkid(nowvo.getGbkNum());
		vo3.setGbkHp(nowvo.getGbkHp());
		vo3.setGbkCon(text_aa.getText());
		vo3.setGbkWrimem(UserVO.getInstance().getId());
		vo3.setGbkMem(homepageMember);
		server.insert(vo3);
		setComment();
		text_aa.clear();
		}else{
			JOptionPane.showMessageDialog(null, "������ �Է��ؿ�");
		}
	}
	public void calcle(ActionEvent event) throws RemoteException{
		parentController.setTableDateList();
		Stage stage = (Stage)button.getScene().getWindow();
		stage.close();
	}
	

}
