package com.light.manager.client;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import com.light.client.file.ImageReceiveClient;
import com.light.messenger.MessengerServerInf;
import com.light.vo.DiaVO;
import com.light.vo.GbkVO;
import com.light.vo.ImgVO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.SplitPane.Divider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DetailController implements Initializable {

	@FXML private Label label_id;
	@FXML private TextField text_tt;
	@FXML private Label label_wr;
	@FXML private Label label_date;
	@FXML private TextArea text_con;
	@FXML private ImageView image_view;

	private BoardController parentController;
	private MessengerServerInf server;
	
	public void setServer(MessengerServerInf server) {
		this.server = server;
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}

	
	
	public void setParentController(BoardController boardController){
		this.parentController = boardController;
	}
	
	
	public void setBoardData(DiaVO vo) throws RemoteException{
			List<DiaVO> list = server.setTabledata(vo);
			vo = list.get(0);
			label_id.setText(vo.getDiaNum());
			text_tt.setText(vo.getDiaTit());
			label_wr.setText(vo.getDiaWrimem());
			label_date.setText(vo.getDiaWridt());
			text_con.setText(vo.getDiaCon());
	}
	public void setBoardData(GbkVO vo) throws RemoteException{
		List<GbkVO> list = server.setTabledata(vo);
		vo = list.get(0);
		label_id.setText(vo.getGbkNum());
		text_tt.setText(vo.getGbkTit());
		label_wr.setText(vo.getGbkWrimem());
		label_date.setText(vo.getGbkWridt());
		text_con.setText(vo.getGbkCon());
	}
	public void setBoardData(ImgVO vo) throws RemoteException{
		int rnd = (int)(Math.random()*(9999-1000+1))+1;
		List<ImgVO> list = server.setTabledata(vo);
		vo = list.get(0);
		label_id.setText(vo.getImgfraNum());
		text_tt.setText(vo.getImgfraTit());
		label_wr.setText(vo.getImgfraWrimem());
		label_date.setText(vo.getImgfraDt());
		text_con.setText(vo.getImgfraCon());
		server.sendImg(vo.getImgfraImg(),rnd);
		ImageReceiveClient imgrc = new ImageReceiveClient();
		image_view.setImage(imgrc.clientRun(rnd));
		
}
	
	
	@FXML
	public void onclickDelete(ActionEvent event) throws RemoteException{
		String num = label_id.getText();
		if(parentController.buttonCheak == 1){
			if(!num.equals("")){
			server.deleteimg(num);
			}
		}
		if(parentController.buttonCheak == 2){
			if(!num.equals("")){
			server.deleteDia(num);
			}
		}
		if(parentController.buttonCheak == 3){
			if(!num.equals("")){
			server.deletegbk(num);
			}
		}
		
		parentController.memSetTableData();
		//자기자신의 창을 닫는다.
		Stage stage = (Stage)label_id.getScene().getWindow();
		stage.close();
		
	}
	
	@FXML
	public void onclickCancle(ActionEvent event){
		//자기자신의 창을 닫는다.
		Stage stage = (Stage)label_id.getScene().getWindow();
		stage.close();
	}
}
