package clientside;
	
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Client extends Application {
	private static Logger log = LogManager.getRootLogger();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/ui/DictionaryView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/ui/application.css").toExternalForm());
			primaryStage.setTitle("Dictionary");
			primaryStage.setResizable(false);
			primaryStage.setAlwaysOnTop(true);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
					log.info("Stage is closing");
	                Platform.exit();
				}
			});
		} catch(Exception e) {
			log.error("Client: FXMLLoader troubles \n" + e.getMessage());
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		log.info("Start Dictionary . . .");
		launch(args);
	}
}
