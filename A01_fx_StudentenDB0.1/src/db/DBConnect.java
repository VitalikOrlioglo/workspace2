package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * Aufgabe: als Singleton implementieren
 * Connetion zur Datenbank herstellen
 * 
 * cimadata Java 2
 * @author micha schirmer 
 * 09.10.2019
 * A01_fx_StudentenDB
 *
 */
public final class DBConnect {
	
	private final String URL ="jdbc:mysql://localhost:3306/java2";
	
	private static DBConnect instance =null;
	
	private Connection con;
	
	private DBConnect() {
		try {
			con = DriverManager.getConnection(URL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized static DBConnect getIntance() {
		if(instance==null) {
			instance = new DBConnect();
		}
		
		return instance;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
}
