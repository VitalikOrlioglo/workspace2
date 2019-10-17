package app;

import java.util.ArrayList;


public class LottoThread extends Thread {
	ArrayList<Integer> zahlenpool = null;
	ArrayList<Integer> luckyNumbers = new ArrayList<>();
	int MAX = 6;    
	int index;
	int ii = 0;
	   
	public void fillPool() {
		zahlenpool = new ArrayList<>();
		for (int i = 1; i <= 49; i++){
			if(luckyNumbers.contains(i)) {
				continue;
			}else {
				zahlenpool.add(i);
			}
		}
		
		try {
			System.out.println("Zahlen-Trommel wird gedreht...");
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void ziehung() {	
		fillPool();
		int index = (int) Math.floor((Math.random() * zahlenpool.size()));
		luckyNumbers.add(zahlenpool.get(index));
		try {
			System.out.println(ii+1 + ". Zahl wird gezogen...");
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("");
		System.out.println(">>> " + luckyNumbers.get(ii) + " <<<    (noch " + zahlenpool.size() + " Zahlen im Pool)");
		System.out.println("");
	}
	
	
	@Override
	public void run(){
		while(ii < 6) {
			try {
				sleep(1000);
				ziehung();
				if(ii == 5) {
					System.out.println("");
					System.out.println("Alle Glückszahlen: " + luckyNumbers);
				}
				ii++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
