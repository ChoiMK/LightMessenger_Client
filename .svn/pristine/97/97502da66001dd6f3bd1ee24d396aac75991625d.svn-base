package com.light.messenger.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.light.messenger.MessengerServerInf;
import com.light.vo.MsgVO;
import com.light.vo.UserVO;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class MsglockerController implements Initializable {
	@FXML ListView list_data;
	@FXML ComboBox comboBox_con;
	private MessengerServerInf server;
	private MsgVO vo = new MsgVO();
	public void setServer(MessengerServerInf server) {
		this.server = server;
		try {
			setData();
		} catch (IOException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			setComboBox();
		} catch (IOException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
	}
	
	public void setComboBox() throws IOException{
		comboBox_con.getItems().addAll("전체쪽지","받은쪽지","보낸쪽지","읽지않은 쪽지");
		comboBox_con.setValue("전체쪽지");
		
	}
	public void setComboBoxData(){
		comboBox_con.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue ov, String t, String t1) {
				if(t1.equals("전체쪽지")){
					vo.setMsgRecmem(null);
					vo.setMsgSenmem(null);
					vo.setMsgWtr(null);
				}else if (t1.equals("받은쪽지")){
					vo.setMsgRecmem(UserVO.getInstance().getId());
					vo.setMsgSenmem(null);
					vo.setMsgWtr(null);
				
				}else if (t1.equals("보낸쪽지")){
					vo.setMsgRecmem(null);
					vo.setMsgSenmem(UserVO.getInstance().getId());
					vo.setMsgWtr(null);
					
				}else if(t1.equals("읽지않은 쪽지")){
					vo.setMsgRecmem(null);
					vo.setMsgSenmem(null);
					vo.setMsgWtr("NO");
					
				}
				try {
					setData();
				} catch (IOException e) {
					// TODO 자동 생성된 catch 블록
					e.printStackTrace();
				}
			}    
		  });
	}
	
	public void setData() throws IOException{
		list_data.getItems().clear();
		vo.setMsgMem(UserVO.getInstance().getId());
		List<MsgVO> list = server.selectMsgList(vo);
		for(int i = 0 ; i<(list.size()+1); i++){
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MsgDetail.fxml"));
		AnchorPane pane = loader.load();
		MsgDetailController msgDetailController = loader.getController();
		msgDetailController.setServer(server);
		msgDetailController.setParentController(this);
		if(i==0){
			msgDetailController.setButton();
			msgDetailController.setimage();
		}else{
			MsgVO vo2 = list.get(i-1);
			msgDetailController.setVo(vo2);
			msgDetailController.setData();
			if(vo2.getMsgWtr().equals("YES")){
				msgDetailController.setimage();
			}
		}
		list_data.getItems().add(pane);
		}
	}

}
