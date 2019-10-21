package app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a.A;

public class Main {
	// ALL -> TRACE -> DEBUG -> INFO -> WARN -> ERROR -> FATAL
	private static Logger log = LogManager.getRootLogger();
	
	public static void main(String[] args) {
		log.info("Programm start . . .");
		A a = new A();
		a.m1(42);
		a.m2(-12);
		a.m3(null);
		
		log.info("Programm ende . . .");
	}

}
