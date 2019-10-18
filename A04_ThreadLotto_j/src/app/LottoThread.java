package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 
 * 
 * Parameter:
 * 
 * Zufallszahlem: Math.random(), new Random()-> nextInt
 * - Lottozahlen/Zahlenpool, alle Zahlen
 * - gezogene Zahlen
 * 
 * z.B. 6 aus 49
 * -keine mehrfachen Zahlen
 * -keine 0
 * Ergebnis: int-Array mit gezogenen Lottozahlen
 * 
 */

public class LottoThread extends Thread {
	int[] lotto;
	@Override
	public void run() {

		int max = 48;
		int anzahl = 7;

		List<Integer> zahlenpool = new ArrayList<Integer>();
//Liste aller 49 Lottozahlen  - einmalig
		for (int i = 0; i <= max; i++) {
			zahlenpool.add(i+1);
		}
//Ab in die Lostrommel
		Collections.shuffle(zahlenpool);

//Lottozahlen ziehen
		lotto = new int[anzahl];
		for (int i = 0; i < anzahl; i++) {
			lotto[i] = zahlenpool.get(i);
			System.out.println(zahlenpool.get(i));
		}

	}

	// evtl. z.B. zusätzliche zahlenZiehen

}
