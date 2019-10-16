package thread;

/*
 * Variante 1 extends Thread
 */
public class Thread1 extends Thread {
	private boolean stop = false;
	
	@Override
	public void run() {
		while (!stop) {
			try {
				Thread.sleep(1000);
				System.out.println("Hallo" + getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// Thread ende -> Thread lebt immer so lange wie lange run() methode lauft
	}
	
	public void stopThread() {
		stop = true;
	}
	
	public static void main(String[] args) {
		Thread1 t1 = new Thread1();
		t1.start();// ruft run
//		t1.run();// hier ist kein threading, sondern ganz normal methode run
//		t1.start();// fehler: nur einmal kann man ein thread zu aufrufen
		
		Thread t2 = new Thread1();
		t2.start();
		
//		t1.stop();// die nicht verwenden, deprecated
		t1.stopThread();
	}

}
