package app;

public class Punkt<T extends Number> {
	private T x; // T ist fur unbekannte type, zb wenn wir nicht wissen was fur ein typ mochten wir benutzen
	private T y;
	public Punkt(T x, T y) {
		super();
		this.x = x;
		this.y = y;
	}
	public T getX() {
		return x;
	}
	public void setX(T x) {
		this.x = x;
	}
	public T getY() {
		return y;
	}
	public void setY(T y) {
		this.y = y;
	}
	
}
