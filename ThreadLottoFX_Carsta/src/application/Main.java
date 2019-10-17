package application;
	
import java.util.List;

import application.LottoThread;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	BorderPane root = null;
	
	@Override
	public void start(Stage primaryStage) {
		LottoThread lotto = new LottoThread();
		lotto.start();
		
		try {
			
			root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,700,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
//		LottoThread lotto = new LottoThread();
//		lotto.start();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
