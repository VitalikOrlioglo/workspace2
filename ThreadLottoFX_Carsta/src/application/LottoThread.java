package application;

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
				SampleController.numberList.add(i);
			}
		}
	}
	
	
	public void ziehung() {	
//		fillPool();
//		int index = (int) Math.floor((Math.random() * zahlenpool.size()));
//		luckyNumbers.add(zahlenpool.get(index));
		
		while(ii < 6) {
			fillPool();
			int index = (int) Math.floor((Math.random() * zahlenpool.size()));
			luckyNumbers.add(zahlenpool.get(index));
			
			if(ii == 5) {
				System.out.println("Alle Glückszahlen: " + luckyNumbers);
				SampleController.numberList = luckyNumbers;
				//SampleController.part3 = SampleController.listToString(luckyNumbers);
			}
			ii++;
		}
	}
	
	
	@Override
	public void run(){

		ziehung();
		
	}

}
