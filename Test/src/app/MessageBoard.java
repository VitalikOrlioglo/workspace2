package app;

import java.util.Observable;

public class MessageBoard extends Observable {
	public void changeMessage(String message) {
		setChanged();
		notifyObservers(message);
	}
}
