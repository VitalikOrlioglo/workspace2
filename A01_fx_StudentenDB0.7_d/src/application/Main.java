package application;
	
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import db.DBConnect;
import db.DBConnectException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	private static Logger log = LogManager.getRootLogger();
	@Override
	public void start(Stage primaryStage) {
		try {
			handleWindowClosing(primaryStage);
//			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Studenten.fxml"));
			FXMLLoader loader = new FXMLLoader();
			loader.setResources(ResourceBundle.getBundle("properties.app")); // app.properties
//			loader.setResources(ResourceBundle.getBundle("properties.app",new Locale("en", "EN")));
			primaryStage.setTitle(loader.getResources().getString("titel"));
			BorderPane root = loader.load(getClass().getResource("Studenten.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
			Platform.exit();
		}
	}

	private void handleWindowClosing(Stage primaryStage) {
		primaryStage.setOnCloseRequest(event -> {
		    System.out.println("Stage is closing");
		    try {
				DBConnect.getIntance().getCon().close();
				Platform.exit();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DBConnectException e) {
				e.printStackTrace();
			}
		});
	}
	
	public static void main(String[] args) {
		log.info("App start  . . .");
		launch(args);
		log.info("App end  . . .");
	}
}
