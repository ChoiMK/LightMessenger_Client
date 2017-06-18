package com.light.manager.client;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;








import com.light.messenger.MessengerServerInf;


import com.light.vo.DiaVO;
import com.light.vo.GbkVO;
import com.light.vo.ImgVO;

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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class BoardController implements Initializable {
	 int buttonCheak ;
	@FXML private ComboBox combo_search;
	@FXML private TextField t_search;
	@FXML private BorderPane borderPane_boardMain;
	@FXML private TableView tableView_content;
	private BoardController boardController = this;
	private MessengerServerInf server;
	
	public void setServer(MessengerServerInf server) {
		this.server = server;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		buttonCheak = ManngerMainController.getButtonCheak();
		setComboBox();
		insertColumn();
		
		
	}
	public void setComboBox(){
		//콤보박스의 값을 "제목", "작성자"로 추가
		combo_search.getItems().addAll("제목","작성자");
		//초기값은 제목
		combo_search.setValue("제목");
	}
	@FXML
	public void onclikDelete(ActionEvent event) throws RemoteException{
		switch (buttonCheak) {
		case 1 :
			ImgVO vo3 = (ImgVO)tableView_content.getSelectionModel().getSelectedItem();
			server.deleteimg(vo3.getImgfraNum());
			tableView_content.getItems().remove(vo3);
			break;
		case 2 :
			DiaVO vo = (DiaVO)tableView_content.getSelectionModel().getSelectedItem();
			 server.deleteDia(vo.getDiaNum());
			tableView_content.getItems().remove(vo);
			break;
		case 3 :
			GbkVO vo2 = (GbkVO)tableView_content.getSelectionModel().getSelectedItem();
			server.deletegbk(vo2.getGbkNum());
			tableView_content.getItems().remove(vo2);
			break;
		}
		
	}
	@FXML 
	public void onclikSearch(ActionEvent event) throws RemoteException{
		
		String title = null;
		String writer = null;
		
		// 콤보박스의 값이 제목이면 검색조건 제목, 작성자면 작성자로
		
		if(combo_search.getValue().toString().equals("제목")){
			title = t_search.getText();
		}else if(combo_search.getValue().toString().equals("작성자")){
			writer = t_search.getText();
		}
		switch (buttonCheak) {
		case 1 : 
			ImgVO vo3 = new ImgVO();
			vo3.setImgfraTit(title);
			vo3.setImgfraWrimem(writer);
			this.setTableData(vo3);
			break;
		case 2 :
			DiaVO vo = new DiaVO();
			vo.setDiaTit(title);
			vo.setDiaWrimem(writer);
			this.setTableData(vo);
			break;
		case 3 :
			GbkVO vo2 = new GbkVO();
			vo2.setGbkTit(title);
			vo2.setGbkWrimem(writer);
			this.setTableData(vo2);

			break;
		}
		
	}
	
	private void setTableData(DiaVO vo) throws RemoteException {
		List<DiaVO> list2 =  server.setTabledata(vo);
		//테이블뷰에 바인드된 옵저버버블 리스트
		ObservableList<DiaVO> diaList;
		//불러온 데이터를 FXCollections통해 옵저버블리스트로 변환
		diaList = FXCollections.observableArrayList(list2);
		//테이블뷰에 옵저져블 리스트를 바인드
		tableView_content.setItems(diaList);
		
	}
	private void setTableData(GbkVO vo) throws RemoteException {
		List<GbkVO> list2 =  server.setTabledata(vo);
		//테이블뷰에 바인드된 옵저버버블 리스트
		ObservableList<GbkVO> diaList;
		//불러온 데이터를 FXCollections통해 옵저버블리스트로 변환
		diaList = FXCollections.observableArrayList(list2);
		//테이블뷰에 옵저져블 리스트를 바인드
		tableView_content.setItems(diaList);
		
	}
	private void setTableData(ImgVO vo) throws RemoteException {
		List<ImgVO> list2 =  server.setTabledata(vo);
		//테이블뷰에 바인드된 옵저버버블 리스트
		ObservableList<ImgVO> diaList;
		//불러온 데이터를 FXCollections통해 옵저버블리스트로 변환
		diaList = FXCollections.observableArrayList(list2);
		//테이블뷰에 옵저져블 리스트를 바인드
		tableView_content.setItems(diaList);
		
	}
	public void insertColumn(){
		switch (buttonCheak) {
		case 1:
			TableColumn<ImgVO, String> tableCol_imgfraNum = new TableColumn<>("고유번호");
			TableColumn<ImgVO, String> tableCol_imgfraTit = new TableColumn<>("제목");
			TableColumn<ImgVO, String> tableCol_imgfraWrimem = new TableColumn<>("작성자");
			TableColumn<ImgVO, String> tableCol_imgfraDt = new TableColumn<>("작성일");
			TableColumn<ImgVO, String> tableCol_imgfraHp = new TableColumn<>("홈페이지고유번호");
			TableColumn<ImgVO, String> tableCol_imgPtkid = new TableColumn<>("게시물여부");
			
			
			tableCol_imgfraNum.setCellValueFactory(new PropertyValueFactory<ImgVO,String>("imgfraNum"));
			tableCol_imgfraTit.setCellValueFactory(new PropertyValueFactory<ImgVO,String>("imgfraTit"));
			tableCol_imgfraWrimem.setCellValueFactory(new PropertyValueFactory<ImgVO,String>("imgfraWrimem"));
			tableCol_imgfraDt.setCellValueFactory(new PropertyValueFactory<ImgVO,String>("imgfraDt"));
			tableCol_imgfraHp.setCellValueFactory(new PropertyValueFactory<ImgVO,String>("imgfraHp"));
			tableCol_imgPtkid.setCellValueFactory(new PropertyValueFactory<ImgVO,String>("imgfraPtkid"));
			
			tableCol_imgfraNum.setMinWidth(100);
			tableCol_imgfraTit.setMinWidth(220);
			tableCol_imgfraWrimem.setMinWidth(150);
			tableCol_imgfraDt.setMinWidth(100);
			tableCol_imgfraHp.setMinWidth(150);
			tableCol_imgPtkid.setMinWidth(150);
		     
			tableView_content.getColumns().addAll(tableCol_imgfraNum,tableCol_imgfraTit,tableCol_imgfraWrimem
					,tableCol_imgfraDt,tableCol_imgfraHp,tableCol_imgPtkid);
			tableView_content.setOnMousePressed(new EventHandler<MouseEvent>() {
		    	  
		    	  @Override
		    	  public void handle(MouseEvent event){
		    		  //이벤트가 마우스 주 버튼 클릭이고, 마우스 클릭 수가 2번이면 더블클릭임
		    		  boolean isPrimaryButtonDown = event.isPrimaryButtonDown();
		    		  int clickCount = event.getClickCount();
		    		  
		    		  if(event.isPrimaryButtonDown() && event.getClickCount()==2){
		    			  //테이블뷰에서 선택된(더블클릭한)아이템을 가져온다.
		    			  ImgVO vo = (ImgVO)tableView_content.getSelectionModel().getSelectedItem();
		    			 
		    			 // 팝업창릉 불러옴
		    			 Stage stage = new Stage();
		    			 
		    			 //Detail.fxml로 부터 컴포넌트를 불러와서 신배치
		    			 try {
		    				 
		    				 FXMLLoader loader = new FXMLLoader(getClass().getResource("Detail.fxml"));
							 FlowPane pane = (FlowPane)loader.load();
							 //디테일 팝업창의 컨트롤로를 가져옴
							 DetailController detailController = loader.getController();
							 //디테일 컨트롤러에 게시판 글번호를 매개변수로 게시판 상세정보 셋
							 detailController.setServer(server);
							 
							 detailController.setBoardData(vo);
							 //디테일 팝업창에서 정보 수정 후 부모창에서도 바뀐 데이터 갱신하도록 메인 컨트롤러를 알려중
							 detailController.setParentController(boardController);
							 
							 //fxml 에서 불러온 패널을 씬에 저장
							 Scene scene = new Scene(pane, 600, 400);
							 
							 //스테이지 씬에 붙임
							 stage.setScene(scene);
							 //스테이지를 보여중
							 stage.show();
						} catch (IOException e) {
							// TODO 자동 생성된 catch 블록
							e.printStackTrace();
						}
		    		  }
		    	  }
			});
			break;
		case 2:
			TableColumn<DiaVO, String> tableCol_diaNum = new TableColumn<>("고유번호");
			TableColumn<DiaVO, String> tableCol_diaTit = new TableColumn<>("제목");
			TableColumn<DiaVO, String> tableCol_diaWrimem = new TableColumn<>("작성자");
			TableColumn<DiaVO, String> tableCol_diaWridt = new TableColumn<>("작성일");
			TableColumn<DiaVO, String> tableCol_diaHp = new TableColumn<>("홈페이지고유번호");
			TableColumn<DiaVO, String> tableCol_diaPtkid = new TableColumn<>("게시물여부"); 
			
			tableCol_diaNum.setCellValueFactory(new PropertyValueFactory<DiaVO,String>("diaNum"));
			tableCol_diaTit.setCellValueFactory(new PropertyValueFactory<DiaVO,String>("diaTit"));
			tableCol_diaWrimem.setCellValueFactory(new PropertyValueFactory<DiaVO,String>("diaWrimem"));
			tableCol_diaWridt.setCellValueFactory(new PropertyValueFactory<DiaVO,String>("diaWridt"));
			tableCol_diaHp.setCellValueFactory(new PropertyValueFactory<DiaVO,String>("diaHp"));
			tableCol_diaPtkid.setCellValueFactory(new PropertyValueFactory<DiaVO,String>("diaPtkid"));
			
			tableCol_diaNum.setMinWidth(100);
			tableCol_diaTit.setMinWidth(220);
			tableCol_diaWrimem.setMinWidth(150);
			tableCol_diaWridt.setMinWidth(100);
			tableCol_diaHp.setMinWidth(150);
			tableCol_diaPtkid.setMinWidth(150);
		     
			tableView_content.getColumns().addAll(tableCol_diaNum,tableCol_diaTit,tableCol_diaWrimem,
					tableCol_diaWridt,tableCol_diaHp,tableCol_diaPtkid);
			
			tableView_content.setOnMousePressed(new EventHandler<MouseEvent>() {
		    	  
		    	  @Override
		    	  public void handle(MouseEvent event){
		    		  //이벤트가 마우스 주 버튼 클릭이고, 마우스 클릭 수가 2번이면 더블클릭임
		    		  boolean isPrimaryButtonDown = event.isPrimaryButtonDown();
		    		  int clickCount = event.getClickCount();
		    		  
		    		  if(event.isPrimaryButtonDown() && event.getClickCount()==2){
		    			  //테이블뷰에서 선택된(더블클릭한)아이템을 가져온다.
		    			  DiaVO vo = (DiaVO)tableView_content.getSelectionModel().getSelectedItem();
		    			 
		    			 // 팝업창릉 불러옴
		    			 Stage stage = new Stage();
		    			 
		    			 //Detail.fxml로 부터 컴포넌트를 불러와서 신배치
		    			 try {
		    				 
		    				 FXMLLoader loader = new FXMLLoader(getClass().getResource("Detail.fxml"));
							 FlowPane pane = (FlowPane)loader.load();
							 //디테일 팝업창의 컨트롤로를 가져옴
							 DetailController detailController = loader.getController();
							 //디테일 컨트롤러에 게시판 글번호를 매개변수로 게시판 상세정보 셋
							 detailController.setServer(server);
							 
							 detailController.setBoardData(vo);
							 //디테일 팝업창에서 정보 수정 후 부모창에서도 바뀐 데이터 갱신하도록 메인 컨트롤러를 알려중
							 detailController.setParentController(boardController);
							 
							 //fxml 에서 불러온 패널을 씬에 저장
							 Scene scene = new Scene(pane, 600, 400);
							 
							 //스테이지 씬에 붙임
							 stage.setScene(scene);
							 //스테이지를 보여중
							 stage.show();
						} catch (IOException e) {
							// TODO 자동 생성된 catch 블록
							e.printStackTrace();
						}
		    		  }
		    	  }
			});
			break;
			
		      
		case 3:
			TableColumn<GbkVO, String> tableCol_gbkNum = new TableColumn<>("고유번호");
			TableColumn<GbkVO, String> tableCol_gbkTit = new TableColumn<>("제목");
			TableColumn<GbkVO, String> tableCol_gbkWridt = new TableColumn<>("작성자");
			TableColumn<GbkVO, String> tableCol_gbkWrimem = new TableColumn<>("작성일");
			TableColumn<GbkVO, String> tableCol_gbkHp = new TableColumn<>("홈페이지고유번호");
			TableColumn<GbkVO, String> tableCol_gbkPtkid = new TableColumn<>("게시물여부");
		
			
			tableCol_gbkNum.setCellValueFactory(new PropertyValueFactory<GbkVO,String>("gbkNum"));
			tableCol_gbkTit.setCellValueFactory(new PropertyValueFactory<GbkVO,String>("gbkTit"));
			tableCol_gbkWridt.setCellValueFactory(new PropertyValueFactory<GbkVO,String>("gbkWrimem"));
			tableCol_gbkWrimem.setCellValueFactory(new PropertyValueFactory<GbkVO,String>("gbkWridt"));
			tableCol_gbkHp.setCellValueFactory(new PropertyValueFactory<GbkVO,String>("gbkHp"));
			tableCol_gbkPtkid.setCellValueFactory(new PropertyValueFactory<GbkVO,String>("gbkPtkid"));
			
			tableCol_gbkNum.setMinWidth(100);
			tableCol_gbkTit.setMinWidth(220);
			tableCol_gbkWridt.setMinWidth(150);
			tableCol_gbkWrimem.setMinWidth(100);
			tableCol_gbkHp.setMinWidth(150);
			tableCol_gbkPtkid.setMinWidth(150);
		     
			tableView_content.getColumns().addAll(tableCol_gbkNum,tableCol_gbkTit,tableCol_gbkWridt,
					tableCol_gbkWrimem,tableCol_gbkHp,tableCol_gbkPtkid);
			tableView_content.setOnMousePressed(new EventHandler<MouseEvent>() {
		    	  
		    	  @Override
		    	  public void handle(MouseEvent event){
		    		  //이벤트가 마우스 주 버튼 클릭이고, 마우스 클릭 수가 2번이면 더블클릭임
		    		  boolean isPrimaryButtonDown = event.isPrimaryButtonDown();
		    		  int clickCount = event.getClickCount();
		    		  
		    		  if(event.isPrimaryButtonDown() && event.getClickCount()==2){
		    			  //테이블뷰에서 선택된(더블클릭한)아이템을 가져온다.
		    			  GbkVO vo = (GbkVO)tableView_content.getSelectionModel().getSelectedItem();
		    			 
		    			 // 팝업창릉 불러옴
		    			 Stage stage = new Stage();
		    			 
		    			 //Detail.fxml로 부터 컴포넌트를 불러와서 신배치
		    			 try {
		    				 FXMLLoader loader = new FXMLLoader(getClass().getResource("Detail.fxml"));
							 FlowPane pane = (FlowPane)loader.load();
							 
							 //디테일 팝업창의 컨트롤로를 가져옴
							 DetailController detailController = loader.getController();
							 //디테일 컨트롤러에 게시판 글번호를 매개변수로 게시판 상세정보 셋
							 detailController.setServer(server);
							 detailController.setBoardData(vo);
							 //디테일 팝업창에서 정보 수정 후 부모창에서도 바뀐 데이터 갱신하도록 메인 컨트롤러를 알려중
							 detailController.setParentController(boardController);
							 
							 //fxml 에서 불러온 패널을 씬에 저장
							 Scene scene = new Scene(pane, 600, 400);
							 
							 //스테이지 씬에 붙임
							 stage.setScene(scene);
							 //스테이지를 보여중
							 stage.show();
						} catch (IOException e) {
							// TODO 자동 생성된 catch 블록
							e.printStackTrace();
						}
		    		  }
		    	  }
			});
			
			
			break;
		}
		
			
	}
	public void memSetTableData() throws RemoteException {
		switch (buttonCheak) {
		case 1:
			List<ImgVO> list =  server.setTabledataimg();
			System.out.println(server);
			System.out.println(list.size());
			//테이블뷰에 바인드된 옵저버버블 리스트
			ObservableList<ImgVO> imgList;
			//불러온 데이터를 FXCollections통해 옵저버블리스트로 변환
			imgList = FXCollections.observableArrayList(list);
			//테이블뷰에 옵저져블 리스트를 바인드
			tableView_content.setItems(imgList);
			break;
		case 2:
			List<DiaVO> list2 = server.setTabledataDia();
			//테이블뷰에 바인드된 옵저버버블 리스트
			ObservableList<DiaVO> diaList;
			//불러온 데이터를 FXCollections통해 옵저버블리스트로 변환
			diaList = FXCollections.observableArrayList(list2);
			//테이블뷰에 옵저져블 리스트를 바인드
			tableView_content.setItems(diaList);
			break;
		case 3:
			List<GbkVO> list3 =  server.setTabledataGbk();
			//테이블뷰에 바인드된 옵저버버블 리스트
			ObservableList<GbkVO> gbkList;
			//불러온 데이터를 FXCollections통해 옵저버블리스트로 변환
			gbkList = FXCollections.observableArrayList(list3);
			//테이블뷰에 옵저져블 리스트를 바인드
			tableView_content.setItems(gbkList);
			break;
		}
		
		
		
	}
	
	
}
