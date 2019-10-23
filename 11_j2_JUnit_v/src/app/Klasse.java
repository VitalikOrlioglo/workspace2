package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Klasse{
	public int max(int a, int b) {
		return a>b?a:b;
	}
	
	public int max(int...arr) {
		int max = arr[0];
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i]>max) {
				max = arr[i];
			}
		}
		return max;
	}
	
	/**
	 * 
	 * @param <T>
	 * @param li - a fur Arraylist, v fur Vector
	 * @return Arraylist, Vector or null
	 */
	public <T> List<T>  createList(String li) {
		if (li.equalsIgnoreCase("v")) {
			return new Vector<>();
		} else if (li.equalsIgnoreCase("a")) {
			return new ArrayList<>();
		}
		return null;
	}
}
