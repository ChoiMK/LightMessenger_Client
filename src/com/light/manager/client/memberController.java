package com.light.manager.client;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;





import com.light.messenger.MessengerServerInf;


import com.light.vo.MemVO;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


//selectList
public class memberController implements Initializable{
	@FXML private ComboBox c_t;
	@FXML private TableView tableView_content;
	private MessengerServerInf server;
	
	public void setServer(MessengerServerInf server) {
		this.server = server;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		insertColumn();
		c_t.setItems(FXCollections.observableArrayList("전체회원","활성화회원","탈퇴회원"));
		
		c_t.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue ov, String t, String t1) {
				try{
				if(t1.equals("활성화회원")){
					memSetTableData("YES");
				}else if (t1.equals("탈퇴회원")){
					memSetTableData("NO");
				}else if (t1.equals("전체회원")){
					memSetTableDataList();
				}
				}catch(Exception e){
					e.printStackTrace();
				}
			}    
		  });
	}
	public void insertColumn(){
		
	      TableColumn<MemVO, String> tableCol_memId = new TableColumn<>("아이디");
	      TableColumn<MemVO, String> tableCol_memNic = new TableColumn<>("닉네임");
	      TableColumn<MemVO, String> tableCol_memPhone = new TableColumn<>("핸드폰번호");
	      TableColumn<MemVO, String> tableCol_memEmail = new TableColumn<>("이메일");
	      TableColumn<MemVO, String> tableCol_memName = new TableColumn<>("이름");
	      TableColumn<MemVO, String> tableCol_memAddress = new TableColumn<>("주소");
	      TableColumn<MemVO, String> tableCol_memBir = new TableColumn<>("생일");
	      TableColumn<MemVO, String> tableCol_memGender = new TableColumn<>("성별");
	      TableColumn<MemVO, String> tableCol_memCheak = new TableColumn<>("활성화상태");

	      
	      
	      tableCol_memId.setCellValueFactory(new PropertyValueFactory<MemVO,String>("memId"));
	      tableCol_memNic.setCellValueFactory(new PropertyValueFactory<MemVO,String>("memNic"));
	      tableCol_memPhone.setCellValueFactory(new PropertyValueFactory<MemVO,String>("memPhonum"));
	      tableCol_memEmail.setCellValueFactory(new PropertyValueFactory<MemVO,String>("memEmail"));
	      tableCol_memName.setCellValueFactory(new PropertyValueFactory<MemVO,String>("memNm"));
	      tableCol_memAddress.setCellValueFactory(new PropertyValueFactory<MemVO,String>("memAdd"));
	      tableCol_memBir.setCellValueFactory(new PropertyValueFactory<MemVO,String>("memBir"));
	      tableCol_memGender.setCellValueFactory(new PropertyValueFactory<MemVO,String>("memGn"));
	      tableCol_memCheak.setCellValueFactory(new PropertyValueFactory<MemVO,String>("memWrlwtr"));
	      
	      tableCol_memId.setMinWidth(75);
	      tableCol_memNic.setMinWidth(75);
	      tableCol_memPhone.setMinWidth(75);
	      tableCol_memEmail.setMinWidth(75);
	      tableCol_memName.setMinWidth(75);
	      tableCol_memAddress.setMinWidth(75);
	      tableCol_memBir.setMinWidth(75);
	      tableCol_memGender.setMinWidth(75);
	      tableCol_memCheak.setMinWidth(75);
	      
	      tableView_content.getColumns().addAll(tableCol_memId,tableCol_memNic,tableCol_memPhone,tableCol_memEmail,tableCol_memName,tableCol_memAddress,
	    		  								tableCol_memBir,tableCol_memGender,tableCol_memCheak);
		
	}
	public void memSetTableData(String values) throws RemoteException{
		
		
		//게시판 리스트를 불러옴
		List<MemVO> list = server.selectMem(values);
		//테이블뷰에 바인드된 옵저버버블 리스트
		ObservableList<MemVO> memList;
		//불러온 데이터를 FXCollections통해 옵저버블리스트로 변환
		memList = FXCollections.observableArrayList(list);
		//테이블뷰에 옵저져블 리스트를 바인드
		tableView_content.setItems(memList);
		
	}
	public void memSetTableDataList() throws RemoteException{
		//게시판 리스트를 불러옴
		List<MemVO> list = server.selectMem();
		//테이블뷰에 바인드된 옵저버버블 리스트
		ObservableList<MemVO> memList;
		//불러온 데이터를 FXCollections통해 옵저버블리스트로 변환
		memList = FXCollections.observableArrayList(list);
		//테이블뷰에 옵저져블 리스트를 바인드
		tableView_content.setItems(memList);
		
	}
	public void out(ActionEvent event) throws RemoteException{
		if(!(c_t.getValue() == null)){
		String menu = c_t.getValue().toString();
		if(menu.equals("활성화회원")||menu.equals("전체회원")){
			MemVO vo = (MemVO) tableView_content.getSelectionModel().getSelectedItem();
			if(!(vo == null)){
			if(menu.equals("활성화회원")){
				vo.setMemWrlwtr("NO");
				server.update(vo);
				memSetTableData("YES");
			}else{
				vo.setMemWrlwtr("NO");
				server.update(vo);
				memSetTableDataList();
				System.out.println(vo.getMemBir());
			}
			}else{
				JOptionPane.showMessageDialog(null, "데이터를 선택해주세요");
			}
		}else{
			JOptionPane.showMessageDialog(null, "이 메뉴에서 사용할 수 없습니다.");
		}
		}else{
			JOptionPane.showMessageDialog(null, "메뉴를 선택하세요");
		}
	}
	public void in(ActionEvent event) throws RemoteException{
		if(!(c_t.getValue() == null)){
		String menu = c_t.getValue().toString();
		if((menu.equals("탈퇴회원")||menu.equals("전체회원"))){
			MemVO vo = (MemVO) tableView_content.getSelectionModel().getSelectedItem();
			if(!(vo == null)){
			if(menu.equals("탈퇴회원")){
				vo.setMemWrlwtr("YES");;
				server.update(vo);
				memSetTableData("NO");
			}else{
				vo.setMemWrlwtr("YES");
				server.update(vo);
				memSetTableDataList();
			}
			}else{
				JOptionPane.showMessageDialog(null, "데이터를 선택해주세요");
			}
		}else{
			JOptionPane.showMessageDialog(null, "이 메뉴에서 사용할 수 없습니다.");
		}
		}else{
			JOptionPane.showMessageDialog(null, "메뉴를 선택하세요");
		}
	}

}
