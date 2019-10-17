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
			
			b.setOnAction(e->{
				Task<String> task = createTask();
				new Thread(task).start();
				
				l1.textProperty().bind(task.messageProperty());
				l2.textProperty().bind(task.valueProperty());
				pi.progressProperty().bind(task.progressProperty());
				pb.progressProperty().bind(task.progressProperty());
				
			});
			
			root.getChildren().addAll(b, l1, l2, pi, pb);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Task ist ein Runnable
	 * Task benotigt Thread
	 */
	public Task<String> createTask() {
		Task<String> task = new Task<String>() {

			@Override
			protected String call() throws Exception {
				updateMessage("call . . ."); // eingeschaft message
				final long MAX = 10_000_000;
				long result = 0;
				
				for (int i = 0; i <= MAX; i++) {
					result+=i;
					updateValue(result+""); // eingeschaft value -> Zwischenergebnis
					updateProgress(i, MAX); // eingeschaft progress
				}
				updateMessage("call end . . . ");
				return "Result: " + result;
			}
		};
		return task;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
