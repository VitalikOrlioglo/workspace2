package app;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
//		LottoThread lotto = new LottoThread();
//		lotto.start();
		int[] array = new int[5];
        Random random = new Random();
		
		new Thread( () -> {
			while (true) {
				try {
					Thread.sleep(1000);
					System.out.println("Ziehung der Lottozahlen - 6 aus 49\\n . . . ");
					for (int i = 0; i < array.length; i++) {
		            	int n = random.nextInt(50);
		            	if(n > 0) {
		                	array[i] = n;
		                }
		    		}
		        	System.out.println(Arrays.toString(array));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} ).start();
	}
}
