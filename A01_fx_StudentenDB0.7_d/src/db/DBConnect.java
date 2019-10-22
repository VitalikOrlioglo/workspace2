package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	private static Logger log = LogManager.getLogger();
	
	//private final String URL ="jdbc:mysql://localhost:3306/java2";
	
	private static DBConnect instance =null;
	
	private Connection con;
	private ResourceBundle res;
	
	private DBConnect() throws DBConnectException {
		
		try {
			res = ResourceBundle.getBundle("properties.app");
			log.trace("load Properties {} ", res.getBaseBundleName());
			con = DriverManager.getConnection(res.getString("url"),res.getString("user"),res.getString("pwd"));
			log.info("Datenbank connected {}", con.getCatalog());
		} catch (SQLException e) {
//			System.err.println("DB Fehler");//log
//			throw new DBConnectException();
			log.error("DB Fehler", e);
			//e.printStackTrace();
		} catch (Exception e) {
			log.error("Fehler", e);
		}
	}
	
	public synchronized static DBConnect getIntance() throws DBConnectException {
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
