package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTest {

	public static void main(String[] args) {
		final String URL = "jdbc:mysql://localhost:3306/java2";// port ist optional, standart ist 3306
		
//		Class.forName("com.mysql.jdbc.Driver").newInstance(); -> ab java 7 optional
		try {
			Connection con = DriverManager.getConnection(URL, "root", "");
			
			schow(con);
			
			//----------------- INSERT
			Statement insertStatement = con.createStatement();
			int n = insertStatement.executeUpdate("INSERT INTO designpattern (name, beschreibung) VALUES ('Factory Method', 'gibt eine Instanz zuruck')"); // fur insert, delete, update
			if (n==1) {
				System.out.println("insert erfolgreich");
			}
			schow(con);
			insertStatement.close();
			
			// ---------------- DELETE
			PreparedStatement deletePreparedStatement = con.prepareStatement("DELETE FROM designpattern WHERE name=?"); // WHERE name=?, beschreibung=? -> parametrisierte queries
			deletePreparedStatement.setString(1, "Factory Method"); // 1 == erste Fragezeichen
			deletePreparedStatement.executeUpdate();
			System.out.println("updateCount delete: " + deletePreparedStatement.getUpdateCount());
			schow(con);
			deletePreparedStatement.close();
			
			// ---------------- UPDATE
			PreparedStatement updatePreparedStatement = con.prepareStatement("UPDATE designpattern SET beschreibung = ? WHERE id=?");
			updatePreparedStatement.setString(1, "Nur ein Objekt");
			updatePreparedStatement.setInt(2, 1); // 2 ist zweite Fragezeichen, 1 ist id
			updatePreparedStatement.executeUpdate();
			System.out.println("updateCount update: " + updatePreparedStatement.getUpdateCount());
			schow(con);
			updatePreparedStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void schow(Connection con) throws SQLException {
		Statement selectStatement = con.createStatement();
		
		ResultSet rs = selectStatement.executeQuery("SELECT * FROM designpattern");
		
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String beschreibung = rs.getString("beschreibung");
			
			System.out.printf("%d %s - %s\n", id, name, beschreibung);
//				System.out.println(id);
//				System.out.println(name);
//				System.out.println(beschreibung);
		}
		System.out.println("************************************************************");
		selectStatement.close();
	}

}
