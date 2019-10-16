package application;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class MyService extends Service<String> {

	@Override
	protected Task<String> createTask() {

		return new Task<String>() {

			@Override
			protected String call() throws Exception {
				updateMessage("Worker startet...");
				double result = 0;
				long MAX = 10000000;
				for (int i = 0; i < MAX; i++) {
					result += i;
					if (isCancelled()) {
						updateMessage("Worker cancelled...");
						break;
					}
					// updateValue();
					updateProgress(i, MAX);
					updateValue(result + "");
					// System.out.println("****" + result);

				}

				updateMessage("Worker fertig...");
				return "Result: " + result;
			}
		};

	}
	
	public synchronized void myWait(){
		try {
			
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
