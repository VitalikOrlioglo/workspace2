package application;
	
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class ServerMain extends Application {
	private static Logger log  = LogManager.getRootLogger();
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Server");
			primaryStage.setAlwaysOnTop(true);
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("TimeServer.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		log.info("start App");
		launch(args);
	}
}
