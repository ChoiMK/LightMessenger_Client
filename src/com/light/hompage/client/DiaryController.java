package com.light.hompage.client;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.light.messenger.MessengerServerInf;
import com.light.vo.DiaVO;
import com.light.vo.GbkVO;
import com.light.vo.UserVO;

public class DiaryController implements Initializable{

	private Pane pane_main;
	private Pane diary_main;
	private MessengerServerInf server;
	private String homepageMember;
	@FXML private TableView tableView_content;
	@FXML private ComboBox comboBox_search;
	@FXML private TextField textField_search;


	public void setServer(MessengerServerInf server) {
		this.server = server;
	}

	public void setHomepageMember(String homepageMember) {
		this.homepageMember = homepageMember;
		
	}

	public Pane getDiary_main() {
		return diary_main;
	}

	public Pane getPane_main() {
		return pane_main;
	}

	private Pane diary2;
	HomeMainController parentController = null;
	
	public void setPane_main(Pane pane_main) {
		this.pane_main = pane_main;
	}
	
	public void setController(HomeMainController parentController) {
		this.parentController = parentController;
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setTable();
		comboBox_search.getItems().addAll(FXCollections.observableArrayList("������","�ۼ���"));

	}
	
	public void onclickDiary(ActionEvent event) throws IOException {
		if(UserVO.getInstance().getId().equals(homepageMember)){
		FXMLLoader diary_loader = new  FXMLLoader(getClass().getResource("Diary2.fxml"));
		diary2 = (Pane) diary_loader.load();
		DiaryController2 diaryController2 =  diary_loader.getController();
		diaryController2.setgController(this);
		diaryController2.setHomepageMember(homepageMember);
		diaryController2.setServer(server);
		diary_main = parentController.getDiary1();
		pane_main.getChildren().removeAll(pane_main.getChildren());
		pane_main.getChildren().add(diary2);
		}else{
			JOptionPane.showMessageDialog(null, "�ڽ��� Ȩ�������� ���� �ۼ� �� �� �ֽ��ϴ�.");	
			}
	}
	public void setTable(){
		TableColumn<DiaVO, String> tableCol_diaNum = new TableColumn<>("��ȣ");
		TableColumn<DiaVO, String> tableCol_diaTit = new TableColumn<>("����");
		TableColumn<DiaVO, String> tableCol_diaWridt = new TableColumn<>("�ۼ���");
		TableColumn<DiaVO, String> tableCol_diaWrimem = new TableColumn<>("�ۼ���");
		
		tableCol_diaNum.setCellValueFactory(new PropertyValueFactory<DiaVO, String>("diaRownum"));
		tableCol_diaTit.setCellValueFactory(new PropertyValueFactory<DiaVO, String>("diaTit"));
		tableCol_diaWridt.setCellValueFactory(new PropertyValueFactory<DiaVO, String>("diaWridt"));
		tableCol_diaWrimem.setCellValueFactory(new PropertyValueFactory<DiaVO, String>("diaWrimem"));
		
		tableCol_diaNum.setMinWidth(100);
		tableCol_diaTit.setMinWidth(300);
		tableCol_diaWridt.setMinWidth(120);
		tableCol_diaWrimem.setMinWidth(140);
		
		tableView_content.getColumns().addAll(tableCol_diaNum, 
				tableCol_diaTit,tableCol_diaWridt,tableCol_diaWrimem);
		
		tableView_content.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					DiaVO vo = (DiaVO)tableView_content.getSelectionModel().getSelectedItem();
					
					Stage stage = new Stage();
					
					
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("Diary3.fxml"));
						BorderPane pane = (BorderPane)loader.load();
						
						DiaryController3 detaillcontrollerDiary = loader.getController();
						detaillcontrollerDiary.setHomepageMember(homepageMember);
						detaillcontrollerDiary.setServer(server);
						detaillcontrollerDiary.setDiaryDate(vo);
						detaillcontrollerDiary.setParentController(DiaryController.this);
						Scene scene = new Scene(pane, 800, 700);
						stage.setScene(scene);
						stage.initModality(Modality.APPLICATION_MODAL); //�θ�â �� ����
						stage.show();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					
				}
			}
		});
	}
	public void setTableDateList() throws RemoteException{
		DiaVO vo = new DiaVO();
		vo.setDiaMem(homepageMember);
		vo.setDiaPtkid("YES");
		List<DiaVO> list = server.setTabledata(vo);
		//���̺�信 ���ε�� ���������� ����Ʈ
		ObservableList<DiaVO> gbklist;
		//�ҷ��� �����͸� FXCollections���� ����������Ʈ�� ��ȯ
		gbklist = FXCollections.observableArrayList(list);
		//���̺�信 �������� ����Ʈ�� ���ε�
		tableView_content.setItems(gbklist);
	}
	public void setTableDateList(DiaVO vo) throws RemoteException{
		vo.setDiaMem(homepageMember);
		vo.setDiaPtkid("YES");
		List<DiaVO> list = server.setTabledata(vo);
		//���̺�信 ���ε�� ���������� ����Ʈ
		ObservableList<DiaVO> gbklist;
		//�ҷ��� �����͸� FXCollections���� ����������Ʈ�� ��ȯ
		gbklist = FXCollections.observableArrayList(list);
		//���̺�信 �������� ����Ʈ�� ���ε�
		tableView_content.setItems(gbklist);
	}
	@FXML
	public void delete(ActionEvent event) throws RemoteException{
		if(UserVO.getInstance().getId().equals(homepageMember)){
		DiaVO vo = (DiaVO)tableView_content.getSelectionModel().getSelectedItem();
		server.deleteDia(vo.getDiaNum());
		setTableDateList();
		}else{
			JOptionPane.showMessageDialog(null, "�ڽ��� Ȩ�������� �����մϴ�.");
		}
	}
	
	@FXML
	public void select(ActionEvent event) throws RemoteException{
		DiaVO vo = new DiaVO();
		if(comboBox_search.getValue() == "������"){
			String title =textField_search.getText();
			vo.setDiaTit(title);
			setTableDateList(vo);
		}else if(comboBox_search.getValue() == "�ۼ���"){
			String witer =textField_search.getText();
			vo.setDiaWrimem(witer);
			setTableDateList(vo);
		}else{
			JOptionPane.showMessageDialog(null, "������ ������ �ּ���");
		}
		
	}
	
	
	

}
