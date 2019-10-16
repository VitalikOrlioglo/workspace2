package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

	public static void main(String[] args) {
//		Punkt<Object> p1 = new Punkt<Object>(2, 4);// wenn wir nicht schreiben typ, dann steht Object

		Punkt<Integer> p1 = new Punkt<>(2, 4); // fur object p1 typ Integer verwendet werden
		p1.setX(3);

		Punkt<Double> p2 = new Punkt<>(2., 4.);
		p2.setX(5d);

		/*
		 * Compilerfehler The type String is not a valid substitute for the bounded
		 * parameter <T extends Number> of the type Punkt<T>
		 */
//		Punkt<String> p3 = new Punkt<>("", "");

		MultiGen<Integer, String, Boolean> mg = new MultiGen<>(2, "", true);

		// Nachteil von Generics -> schlechte lesbarkeit
		HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<String, Integer>>>> map = new HashMap<>();

		/*
		 * generic mit fragezeichen anlich mit const auf C++
		 */
		ArrayList<?> aList1 = new ArrayList<>();
		aList1.add(""); // fehler: The method add(capture#1-of ?) in the type ArrayList<capture#1-of ?>
						// is not applicable for the arguments (String)

		// ? unveranderlich
		List<?> aList2 = Arrays.asList("aaaaaa", "bbbbbb", "ccccccc");
		aList2.add(""); // fehler: ? Platzhalter, aber nicht mehr zu verandern

		// Reflection: kommt spater
		Class c = Punkt.class;
		Class<?> c1 = Punkt.class;
	}

}
