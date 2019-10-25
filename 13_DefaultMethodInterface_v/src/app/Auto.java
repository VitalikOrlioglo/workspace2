package app;

import java.util.List;

public class Auto implements Fahrzeug, Fahrzeug2 {

	@Override
	public void fahre() {
		List<String> li;
//		Fahrzeug.super.stop();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		Fahrzeug2.super.stop();
	}
}
