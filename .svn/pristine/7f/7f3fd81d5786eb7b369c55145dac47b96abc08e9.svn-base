package application;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import com.light.client.file.ImageReceiveClient;
import com.light.hompage.client.HomeMainController;
import com.light.messenger.MessengerServerInf;
import com.light.messenger.client.MsglockerController;
import com.light.messenger.client.ProfileSettingController;
import com.light.vo.MemVO;
import com.light.vo.UserVO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MyController implements Initializable {
	@FXML Label label_name;
	@FXML private ImageView image_main;
	private MessengerServerInf server;
	
	public void setServer(MessengerServerInf server) {
		this.server = server;
		try {
			setImage();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		label_name.setText(UserVO.getInstance().getId());
		
	}
	
	public void setImage() throws RemoteException{
		int rnd = (int)(Math.random()*(9999-1000+1))+1;
		MemVO vo = new MemVO();
		vo.setMemId(UserVO.getInstance().getId());
		List<MemVO> list = server.selectmemList(vo);
		vo = list.get(0);
		if(vo.getMemImg() != null){
		server.sendImg(vo.getMemImg(),rnd);
		ImageReceiveClient imgrc = new ImageReceiveClient();
		image_main.setImage(imgrc.clientRun(rnd));
		image_main.setFitHeight(50);
		image_main.setFitWidth(45);
		}
		
	}
	@FXML
	public void profileSet(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/light/messenger/client/ProfileSetting.fxml"));
		BorderPane pane = (BorderPane) loader.load();
		ProfileSettingController profileSettingController = (ProfileSettingController)loader.getController();
		profileSettingController.setServer(server);
		profileSettingController.setData();
		profileSettingController.setMyController(this);
		Scene scene = new Scene(pane, 390, 690);
		Stage stage = new Stage();
		stage.setScene(scene);
		
		stage.initModality(Modality.APPLICATION_MODAL); //부모창 못 닫음
		stage.show();
	}
	@FXML
	public void myHome(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/light/hompage/client/Main.fxml"));
		BorderPane root = (BorderPane) loader.load();
		root.getStylesheets().add("/com/light/view/styles/Messenger_style.css");
		HomeMainController chilernController = (HomeMainController)loader.getController();
		chilernController.setHomepageMember(UserVO.getInstance().getId());
		chilernController.setServer(server);
		Scene scene = new Scene(root,1040,620);
		Stage stage = new Stage();
		stage.setScene(scene);
		
		stage.initModality(Modality.APPLICATION_MODAL); //부모창 못 닫음
		stage.show();
	}
	
	//Msglocker.fxml
	@FXML
	public void msglocker(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/light/messenger/client/Msglocker.fxml"));
		BorderPane pane = (BorderPane) loader.load();
		MsglockerController msglockerController = loader.getController();
		
		msglockerController.setServer(server);
		msglockerController.setComboBoxData();
		Scene scene = new Scene(pane, 800, 800);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL); //부모창 못 닫음
		stage.show();
	}
	
	public void close(){
		Stage stage = (Stage) label_name.getScene().getWindow();
		stage.close();
	}

}
