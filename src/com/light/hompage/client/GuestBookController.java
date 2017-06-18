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
import com.light.vo.GbkVO;
import com.light.vo.UserVO;

public class GuestBookController implements Initializable{
	
	private Pane pane_main;
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

	public Pane getPane_main() {
		return pane_main;
	}
	private Pane guest_main;
	
	public Pane getGuest_main() {
		return guest_main;
	}
	private Pane guestBook2;
	HomeMainController parentController = null;
	
	public void setPane_main(Pane pane_main){
		this.pane_main = pane_main;
	}
	
	public void setController(HomeMainController parentController) {
		this.parentController = parentController;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			setTableView();
			comboBox_search.getItems().addAll(FXCollections.observableArrayList("글제목","작성자"));
		
	}
	public void onclickGuest(ActionEvent event) throws IOException {
		FXMLLoader guest_loader = new FXMLLoader(getClass().getResource("GuestBook2.fxml"));
		guestBook2 = (Pane)guest_loader.load();
		GuestBookController2 guestController2 = guest_loader.getController();
		guestController2.setgController(this);
		guestController2.setServer(server);
		guestController2.setHomepageMember(homepageMember);
		
		guest_main = parentController.getGuestBook1();
		pane_main.getChildren().removeAll(pane_main.getChildren());
		pane_main.getChildren().add(guestBook2);
	}
	public void setTableView() {
		TableColumn<GbkVO, String> tableCol_gbkNum = new TableColumn<>("번호");
		TableColumn<GbkVO, String> tableCol_gbkTit = new TableColumn<>("제목");
		TableColumn<GbkVO, String> tableCol_gbkWridt = new TableColumn<>("작성일");
		TableColumn<GbkVO, String> tableCol_gbkWrimem = new TableColumn<>("작성자");
		
		tableCol_gbkNum.setCellValueFactory(new PropertyValueFactory<GbkVO, String>("gbkRownum"));
		tableCol_gbkTit.setCellValueFactory(new PropertyValueFactory<GbkVO, String>("gbkTit"));
		tableCol_gbkWridt.setCellValueFactory(new PropertyValueFactory<GbkVO, String>("gbkWridt"));
		tableCol_gbkWrimem.setCellValueFactory(new PropertyValueFactory<GbkVO, String>("gbkWrimem"));
		
		tableCol_gbkNum.setMinWidth(100);
		tableCol_gbkTit.setMinWidth(300);
		tableCol_gbkWridt.setMinWidth(120);
		tableCol_gbkWrimem.setMinWidth(140);
		
		tableView_content.getColumns().addAll(tableCol_gbkNum,tableCol_gbkTit,
				tableCol_gbkWridt,tableCol_gbkWrimem);
		
		tableView_content.setOnMousePressed(new EventHandler <MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				
				if(event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					GbkVO vo = (GbkVO)tableView_content.getSelectionModel().getSelectedItem();
					
					
					
					try {
						Stage stage = new Stage();
						FXMLLoader loader = new FXMLLoader(getClass().getResource("GuestBook3.fxml"));
						BorderPane pane = (BorderPane)loader.load();
						DetaillController detaillcontroller = (DetaillController)loader.getController();
						detaillcontroller.setServer(server);
						detaillcontroller.setHomepageMember(homepageMember);
						detaillcontroller.setGuestBookDate(vo);
						detaillcontroller.setParentController(GuestBookController.this);
						
						Scene scene = new Scene(pane, 800, 700);
						stage.setScene(scene);
						stage.initModality(Modality.APPLICATION_MODAL); //부모창 못 닫음
						stage.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}
	public void setTableDateList() throws RemoteException{
		GbkVO vo = new GbkVO();
		vo.setGbkMem(homepageMember);
		vo.setGbkPtkid("YES");
		List<GbkVO> list = server.setTabledata(vo);
		//테이블뷰에 바인드된 옵저버버블 리스트
		ObservableList<GbkVO> gbklist;
		//불러온 데이터를 FXCollections통해 옵저버블리스트로 변환
		gbklist = FXCollections.observableArrayList(list);
		
		//테이블뷰에 옵저져블 리스트를 바인드
		tableView_content.setItems(gbklist);
	}
	
	@FXML
	public void delete(ActionEvent event) throws RemoteException{
		GbkVO vo = (GbkVO)tableView_content.getSelectionModel().getSelectedItem();
		if(UserVO.getInstance().getId().equals(homepageMember)||UserVO.getInstance().getId().equals(vo.getGbkWrimem())){
		server.deletegbk(vo.getGbkNum());
		setTableDateList();
		}else{
			JOptionPane.showMessageDialog(null, "본인의 미니홈페이나 본인이 쓴글만 가능합니다.");
		}
	}
	public void setTableDateList(GbkVO vo) throws RemoteException{
		vo.setGbkMem(homepageMember);
		vo.setGbkPtkid("YES");
		List<GbkVO> list = server.setTabledata(vo);
		//테이블뷰에 바인드된 옵저버버블 리스트
		ObservableList<GbkVO> gbklist;
		//불러온 데이터를 FXCollections통해 옵저버블리스트로 변환
		gbklist = FXCollections.observableArrayList(list);
		//테이블뷰에 옵저져블 리스트를 바인드
		tableView_content.setItems(gbklist);
	}
	
	@FXML
	public void select(ActionEvent event) throws RemoteException{
		GbkVO vo = new GbkVO();
		if(comboBox_search.getValue() == "글제목"){
			String title =textField_search.getText();
			vo.setGbkTit(title);
			setTableDateList(vo);
		}else if(comboBox_search.getValue() == "작성자"){
			String witer =textField_search.getText();
			vo.setGbkWrimem(witer);
			setTableDateList(vo);
		}else{
			JOptionPane.showMessageDialog(null, "조건선택!!");
		}
		
	}
	
	

}
