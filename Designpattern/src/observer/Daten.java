package observer;

import java.time.LocalDate;
import java.util.Observable;
// Subjekt/Observer
public class Daten extends Observable{
	int count;
	public void start() {
		while (true) {
			try {
				Thread.sleep(1000);
				count++;
				setChanged(); // Daten geändert
				notifyObservers(LocalDate.now()); // Daten senden
				System.out.println("Data: " + count);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
