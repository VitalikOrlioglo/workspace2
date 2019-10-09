package singleton;


public class Singleton {
	private static Singleton instance = null;

	private Singleton() {}
	
	/*
	 * synchronized == Threadsicher
	 */
	public synchronized static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
	
	// hier normale andere Methoden
	public void m() {}
	
}
