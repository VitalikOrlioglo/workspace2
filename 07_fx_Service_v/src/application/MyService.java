package application;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class MyService extends Service<String>{

	@Override
	protected Task<String> createTask() {
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
	
}
