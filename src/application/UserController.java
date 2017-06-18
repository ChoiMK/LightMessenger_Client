package application;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.light.client.file.ImageReceiveClient;
import com.light.hompage.client.HomeMainController;
import com.light.manager.client.ManngerMainController;
import com.light.message.client.Client;
import com.light.messenger.MessengerServerInf;
import com.light.messenger.client.ChatMainController;
import com.light.messenger.client.MessengerController;
import com.light.messenger.client.SendMsgController;
import com.light.vo.FdVO;
import com.light.vo.MemVO;
import com.light.vo.UserVO;

public class UserController implements Initializable {
	@FXML Label label_name;
	
	private MessengerServerInf server;
	private String selectMember;
	private Client client;
	@FXML ImageView image_main;
	@FXML ImageView image_onOff;
	MessengerController messengerController;
	String fdnum;
	public void setMessengerController(MessengerController messengerController) {
		this.messengerController = messengerController;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public void setServer(MessengerServerInf server) {
		this.server = server;
	}
	
	
	
	
	
	
	public List<FdVO> getFriendList(int count){
		List<FdVO> list = new ArrayList<FdVO>();
		int rnd = (int)(Math.random()*(9999-1000+1))+1;
		try {
			list = this.server.getUserList(UserVO.getInstance().getId());
			label_name.setText(list.get(count).getFdLt());
			MemVO vo = new MemVO();
			vo.setMemId(list.get(count).getFdLt());
			List<MemVO> list2 = server.selectmemList(vo);
			vo = list2.get(0);
			fdnum = list.get(count).getFdRegnum();
			if(vo.getMemImg() != null){
				server.sendImg(vo.getMemImg(),rnd);
				ImageReceiveClient imgrc = new ImageReceiveClient();
				image_main.setImage(imgrc.clientRun(rnd));
				image_main.setFitHeight(50);
				image_main.setFitWidth(45);
			}
			
			String userId = UserVO.getInstance().getId();
			
			ArrayList<String> onlist = server.userOnOff(userId);
			
			if(onlist.size()>0){
				for(int j = 0;  j<onlist.size();j++){
					if(vo.getMemId().equals(onlist.get(j))){
						this.onOffset();
					}
				}
			}
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return list; 
	}
	
	public void onOffset(){
		Image img2 = new Image(getClass().getResource("../com/light/view/images/on2.png").toString());
		image_onOff.setImage(img2);
	}
	
	@FXML
	public void chat(ActionEvent event){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/light/messenger/client/ChatMain.fxml"));
			BorderPane root = (BorderPane)loader.load();
			root.getStylesheets().add("/com/light/view/styles/Messenger_style.css");
			
			ChatMainController chatMainController = loader.getController();
			chatMainController.setServer(server);
			chatMainController.setClient(client);
			
			String name = this.label_name.getText();
			chatMainController.setName(name);
			
			
			Scene scene = new Scene(root,350,550);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			
			
			
			chatMainController.newChat();
		
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void msg(ActionEvent event){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/light/messenger/client/SendMsg.fxml"));
			BorderPane root = (BorderPane)loader.load();
			SendMsgController sendMsgController = loader.getController();
			sendMsgController.setServer(server);
			
			String name = this.label_name.getText();
			sendMsgController.setName(name);
			
			Scene scene = new Scene(root,400,400);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void home(ActionEvent event) throws IOException{
		selectMember = this.label_name.getText();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/light/hompage/client/Main.fxml"));
		BorderPane root = (BorderPane)loader.load();
		root.getStylesheets().add("/com/light/view/styles/Messenger_style.css");
		Scene scene = new Scene(root,1040,620);
		HomeMainController mainController = (HomeMainController)loader.getController();
		mainController.setHomepageMember(selectMember);
		mainController.setServer(server);
		Stage mainStage = new Stage();
		mainStage.setScene(scene);
		mainStage.initModality(Modality.APPLICATION_MODAL); 
		mainStage.show();
		
	}
	
	@FXML
	public void fdDelete(MouseEvent event) throws RemoteException{
		if(event.getButton().toString().equals("SECONDARY")){
			int result = JOptionPane.showConfirmDialog(null, label_name.getText()+
					"님를 삭제 하시겠습니까?","친구삭제",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if(result == 0){
				server.deleteFd(fdnum);
				messengerController.getUserList();
			}
			
		}
	}
	

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}