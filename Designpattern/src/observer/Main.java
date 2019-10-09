package observer;

public class Main {

	public static void main(String[] args) {
		Daten daten = new Daten();
		daten.addObserver(new MyObserver()); // alle Methoden mit add.. sind die Liste
		daten.start();
	}

}
