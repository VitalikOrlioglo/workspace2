package thread;

/*
 * Variante 2 implements Runnable 
 */
public class Runnable1 implements Runnable{

	public static void main(String[] args) {
		Thread t1 = new Thread( new Runnable1() );
		t1.start();
		
		
		
//		Thread t2 =
		new Thread( () -> {
			while (true) {
				try {
					Thread.sleep(1000);
					System.out.println("run . . . ");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} ).start();
//		t2.start();
		
		
	}

	
	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				System.out.println("run . . . ");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
