package observer;

import java.util.Observable;
import java.util.Observer;

public class MyObserver implements Observer{

	@Override
	public void update(Observable o, Object obj) {
		Daten d = (Daten) o;
		System.out.println("MyObserver: " + d.count + "" + obj);
	}
	
}
