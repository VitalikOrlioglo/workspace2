package db;

public class DBConnectException extends Exception {

	public DBConnectException() {
	}

	public DBConnectException(String message) {
		super(message);
	}

	public DBConnectException(Throwable cause) {
		super(cause);
	}

}
