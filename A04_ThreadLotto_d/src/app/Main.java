package app;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		LottoThread lotto = new LottoThread(49, 6);
		lotto.start();
		
		try {
			lotto.join();// hält den Hauptthread
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Arrays.toString(lotto.getZahlen()));

	}

}
