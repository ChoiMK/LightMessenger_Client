package com.light.hompage.client;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import com.light.client.file.TCPFileClient;
import com.light.messenger.MessengerServerInf;
import com.light.vo.HpVO;
import com.light.vo.ImgVO;
import com.light.vo.UserVO;

public class PhtoConutroller2 implements Initializable{

	PhtoConutroller parentController;
	private Pane main_pane;
	private Pane phto1;
	private MessengerServerInf server;
	private String homepageMember;
	private ImgVO vo2 = new ImgVO();
	private String Imge = "";
	@FXML private TextField text_title;
	@FXML private TextArea text_content;
	@FXML private ImageView image_insert;
	File file;

	public void setServer(MessengerServerInf server) {
		this.server = server;
	}
	
	public void setHomepageMember(String homepageMember) {
		this.homepageMember = homepageMember;
	}
	public void setgController(PhtoConutroller parentController) {
		this.parentController = parentController;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	@FXML
	public void setOnclickPhtoCancel(ActionEvent event) {
		main_pane = parentController.getPane_main();
		phto1 = parentController.getPhto_main();
		main_pane.getChildren().removeAll(main_pane.getChildren());
		main_pane.getChildren().add(phto1);
		
	}
	@FXML
	public void saveData(ActionEvent event) throws IOException{
		String title = text_title.getText();
		String content = text_content.getText();
		HpVO vo = new HpVO();
		vo.setHpMem(homepageMember);
		List<HpVO> list = server.selectHp(vo);
		vo = list.get(0);
		vo2.setImgfraPtkid("YES");
		vo2.setImgfraMem(homepageMember);
		vo2.setImgfraWrimem(UserVO.getInstance().getId());
		vo2.setImgfraCon(content);
		vo2.setImgfraHp(vo.getHpCd());
		vo2.setImgfraTit(title);
		vo2.setImgfraImg(Imge);
		server.insert(vo2);
		parentController.homeList(null);;
		setOnclickPhtoCancel(null);
	}
	@FXML
	public void setImage(ActionEvent event){
		FileChooser fileChooser = new FileChooser();
		TCPFileClient TCPC = new TCPFileClient();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        fileChooser.getExtensionFilters().add(extFilter);
        file = fileChooser.showOpenDialog(null);
      
        	Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						Imge = server.fileinsert();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					
				}
			});
        thread.start();
        TCPC.clientRun(file.getPath());
        Image img = new Image("file:/"+file.getPath());
        image_insert.setImage(img);
	}
	
	

}
