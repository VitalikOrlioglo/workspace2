package application;
	
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			TilePane root = new TilePane();
			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Button b = new Button("Start");
			Label l1 = new Label();
			Label l2 = new Label();
			ProgressIndicator pi = new ProgressIndicator();
			ProgressBar pb = new ProgressBar();
			MyService myService = new MyService();
			
			l1.textProperty().bind(myService.messageProperty());
			l2.textProperty().bind(myService.valueProperty());
			pi.progressProperty().bind(myService.progressProperty());
			pb.progressProperty().bind(myService.progressProperty());
			
			b.setOnAction(e->{
				myService.restart(); // start;
			});
			
			/*
			 * 3 variante Wert of variable zu bekommen
			 * - setOnSucceeded
			 * ValueProperty Listener
			 * ValueProperty Binding
			 */
			myService.setOnSucceeded(e->{
				System.out.println("Service ende");
			});
			
			myService.setOnFailed(e->{
				// fur Meldungen
			});
			
			// Exceptionhandling fur call-Methoden
			myService.exceptionProperty().addListener((a, oldValue, newValue) -> {
				if (newValue!=null) {
					Throwable ex = newValue;
					ex.printStackTrace();
				}
			});
			root.getChildren().addAll(b, l1, l2, pi, pb);
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
