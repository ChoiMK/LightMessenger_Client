package application;
	
import javafx.animation.StrokeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane root2=new GridPane();
			Text text=new Text("LIGHT");
		    text.setFill(Color.TRANSPARENT);
		    text.setStrokeWidth(3);
		    text.setFont(Font.font("Impact",80)); 
		    text.layoutXProperty();
		    text.autosize();
		    root2.setPadding(new Insets(100, 100, 100, 120));
			
		    StrokeTransition text_stroke_trans = new StrokeTransition(Duration.millis(300),text, Color.GOLD,   Color.LIGHTGREEN);
		    text_stroke_trans.setDelay(Duration.millis(10));
		    text_stroke_trans.setCycleCount(Timeline.INDEFINITE); 
		    text_stroke_trans.setAutoReverse(true);
		    
		    text_stroke_trans.play();
		    root2.getChildren().addAll(text);
			
		    FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
			BorderPane root = (BorderPane)mainLoader.load();
			root.getStylesheets().add("/com/light/view/styles/Messenger_style.css");
			root.getChildren().add(root2);
			Scene scene = new Scene(root,390,690);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
