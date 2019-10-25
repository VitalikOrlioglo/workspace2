package app;

public interface Fahrzeug {
	public void fahre();
	
	// ab Java 8 konnen wir in interface statische methoden schreiben
	public static void info() {
		System.out.println("Fahrzeug . . .");
	}
	
	// ab Java 8 konnen wir in interface nicht nur methoden signature schreiben,
	// sondern ganze methoden, aber mit default
	public default void stop() {
		System.out.println("Fahrzeug stop . . .");
	}
}
