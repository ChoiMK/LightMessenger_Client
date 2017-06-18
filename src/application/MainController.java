package application;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.light.manager.client.ManngerMainController;
import com.light.member.IdFindControll;
import com.light.member.JoinController;
import com.light.member.PwFindController;
import com.light.message.client.Client;
import com.light.messenger.MessengerClientInf;
import com.light.messenger.MessengerServerInf;
import com.light.messenger.client.MessengerController;
import com.light.vo.UserVO;


public class MainController implements Initializable {
	
	@FXML private TextField t_id;
	@FXML private PasswordField t_pw;
	MessengerController messengerController;
	private MessengerServerInf server;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			server =(MessengerServerInf)Naming.lookup("rmi://192.168.201.252/chat");
		} catch (MalformedURLException e) {
			e.printStackTrace();
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void join(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/light/member/Join.fxml"));
		BorderPane root = (BorderPane)loader.load();
		Scene scene = new Scene(root,500,500);
		
		
		JoinController joinController = (JoinController)loader.getController(); 
		joinController.setServer(this.server);
		
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL); 
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void idFind(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/light/member/IdFind.fxml"));
		BorderPane root = (BorderPane)loader.load();
		Scene scene = new Scene(root,400,300);
		
		IdFindControll idFindController = (IdFindControll)loader.getController(); 
		idFindController.setServer(this.server);
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL); 
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void pwFind(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/light/member/PwFind.fxml"));
		BorderPane root = (BorderPane)loader.load();
		Scene scene = new Scene(root,400,380);
		
		PwFindController pwFindController = (PwFindController)loader.getController(); 
		pwFindController.setServer(this.server);
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL); 
		stage.setScene(scene);
		stage.show();
	}
		
	@FXML 
	public void login(ActionEvent event){
		
			Stage stage=(Stage)t_id.getScene().getWindow();
			
			String id = t_id.getText();
			String pw = t_pw.getText(); 
			boolean user;
			try {
				if(!(id.equals("test")&&pw.equals("1111"))){
				    
					user = server.loginCheck(id, pw);
					
					if (user) {
						UserVO.getInstance().setId(id);
						Client client = new Client();
						
						
						server.contactList(id, client);  //아이디와 클라이언트를 보낸다
						
						
						ArrayList<MessengerClientInf> clientList= server.clientList(id);
						
						
						if(clientList.size()>0){
							for(int j = 0; j<clientList.size();j++){
								clientList.get(j).getUserList();
							}
						}
						changeScene(client); // 메신저화면으로 전환
						client.setMessengerController(messengerController);
						
					} else {
						
//						Dialogs.showConfirmDialog(stage, "실패");
					}
				}else{
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/light/manager/client/ManageMember.fxml"));
					BorderPane root = (BorderPane)loader.load();

					Scene scene = new Scene(root,800,500);
					root.getStylesheets().add("/com/light/view/styles/Messenger_style.css");

					ManngerMainController mainController = (ManngerMainController)loader.getController();
					mainController.setServer(server);
					Stage mainStage = new Stage();
					mainStage.setScene(scene);
					mainStage.show();
					
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			

		
	}
	
	public void changeScene(Client client){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/light/messenger/client/Messenger.fxml"));
			BorderPane root = (BorderPane)loader.load();
			
			Scene scene = new Scene(root,380,680);
			 messengerController = (MessengerController)loader.getController();
			root.getStylesheets().add("/com/light/view/styles/Messenger_style.css");
			messengerController.setServer(server);
			messengerController.setClient(client);
			
			messengerController.getUserList();
		
			Stage mainStage = (Stage)t_id.getScene().getWindow();
			mainStage.setScene(scene);
			mainStage.show();
			mainStage.setResizable(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
	

