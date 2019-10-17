package app;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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

	private Integer[] zahlen;

	private int pool;
	private int count;

	public LottoThread(int pool, int count) {
		this.pool = pool;
		this.count = count;
	}

	public Integer[] getZahlen() {
		return zahlen;
	}

	@Override
	public void run() {
		Random rand = new Random();
		Set<Integer> set = new HashSet<>();
		while (set.size() < count) {
			set.add(rand.nextInt(pool) + 1);
		}
		zahlen = set.toArray(new Integer[count]);

	}

}
