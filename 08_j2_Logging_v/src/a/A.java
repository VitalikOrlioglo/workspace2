package a;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class A {
	private static Logger log = LogManager.getLogger(A.class); // = LogManager.getLogger();
	
	public void m1(int n) {
		int b = n*3;
		log.debug("n: {}", n);
		log.trace("b: {}", b);
	}
	
	public void m2(int temperature) {
		if (temperature < 0) {
			log.warn("Temperature unter 0: {}", temperature);
		}
	}
	
	public void m3(String s) {
		try {
			System.out.println(s.length());
		} catch (Exception e) {
//			log.error("Fheler:" + s + " " + e.fillInStackTrace());
			log.error("Fheler:", e);
		}
	}
}
