package app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a.A;

/*
 * log4j.xml standarf im src. Wenn anderer Ort, dann als Parameter in run-configuration:
 * - Dlog4j.configuration = file:///your/path/log4j.xml
 */

public class Main {
	/*
	 * Log level
	 * ALL -> TRACE -> DEBUG -> INFO -> WARN -> ERROR -> FATAL
	 */
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
