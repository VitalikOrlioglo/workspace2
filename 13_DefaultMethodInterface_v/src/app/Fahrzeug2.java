package app;

interface X{
	public default void stop() {
		
	}
}

public interface Fahrzeug2 extends X {
	
	public default void stop() {
		System.out.println("Fahrzeug2 stop . . .");
	}
}
