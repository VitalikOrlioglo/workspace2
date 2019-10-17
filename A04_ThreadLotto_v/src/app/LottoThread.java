package app;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/*
 * Parameter:
 * Zufallszahlen: Math.random(), new Random()-> nextInt
 * - Lottozahlen/ Zahlenpool, alle Zahlen
 * - gezogene Zahlen
 * 
 * z.B. 6 aus 49
 * - keine mehrfachen Zahlen
 * - keine 0
 * Ergebnis: int-Array mit gezogenen Lottozahlen
 */
public class LottoThread extends Thread {
	
	public int randomNumberInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
	
	@Override
	public void run() {
		Scanner input = new Scanner(System.in);
		System.out.println("Geben Sie die Anzahl der Versuche ein: ");
		int quantität = input.nextInt();
		
		System.out.println("Geben Sie die Lottolänge ein: ");
		int size = input.nextInt();
		
		System.out.println("Geben Sie eine Gewinnzahl ein: ");
		int gewinnZahl = input.nextInt();
		input.close();
		
		int[] array = new int[size];
        
        for (int i = 0; i < quantität; i++) {
        	try {
				Thread.sleep(1000);
				System.out.println("Ziehung der Lottozahlen . . .\n");
	        	for (int j = 0; j < array.length; j++) {
	        		array[j] = randomNumberInRange(1, 50);
	    		}
	        	System.out.println(Arrays.toString(array));
	        	System.out.println(array[size-1]==gewinnZahl ? "Sie haben gewonnen!" : "Schade, versuchen Sie noch einmal!");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        	

	}
	// evtl. z.B. zusatzliche zahlenZiehen 
}
