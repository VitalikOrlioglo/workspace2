package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import db.DBConnect;
import db.DBConnectException;
import model.Student;

public class StudentMySQLDAOImpl implements StudentDAO {
	private static Logger log = LogManager.getLogger();
	private DBConnect dbconnect ;
	
	
	public StudentMySQLDAOImpl() throws DBConnectException {
		dbconnect = DBConnect.getIntance();
	}

	@Override
	public List<Student> findAll() {
		List<Student> list = null;
		try {
			PreparedStatement selectStatement = dbconnect.getCon().prepareStatement("SELECT * FROM student");
			ResultSet rs = selectStatement.executeQuery();
			list = createResultList( rs);

		} catch (SQLException e) {
//			e.printStackTrace();//log
			log.error(e);
		}
		return list;
	}

	@Override
	public boolean deleteStudent(int id) {
		log.debug(id);
		try {
			PreparedStatement deleteStatement = dbconnect.getCon().prepareStatement("DELETE FROM student WHERE id=?");
			deleteStatement.setInt(1, id);
			boolean deleted = deleteStatement.executeUpdate() == 1;
			log.info("geloscht {}", deleted);
			return deleted;
		} catch (SQLException e) {
			log.error("id: {}", id, e);
		}
		return false;
	}

	@Override
	public boolean save(Student student) {
		log.debug(student);
		try {
			PreparedStatement ps = dbconnect.getCon().prepareStatement(
					"INSERT INTO student (matrikelnummer,vorname, nachname, geburtsdatum,bild)" + "VALUES (?,?,?,?,?)");
			ps.setString(1, student.getMatrikelnummer());
			ps.setString(2, student.getVorname());
			ps.setString(3, student.getNachname());
			ps.setDate(4, Date.valueOf(student.getGeburtsdatum())); // convert Localdate to sql.Date
			ps.setString(5, student.getBild());
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateStudent(int id, String fieldName, String newValue) {
		try {
			PreparedStatement updateStatement = dbconnect.getCon()
					.prepareStatement("UPDATE student SET " + fieldName + " =? WHERE id=?");
			
			updateStatement.setString(1, newValue);
			updateStatement.setInt(2, id);
			return updateStatement.executeUpdate()==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public List<Student> findByField(String fieldname, String searchstring) {
		List<Student> list=null; 
		final String SQL = "SELECT * FROM student WHERE "+fieldname+" LIKE ?";
		try (PreparedStatement selectStatement = dbconnect.getCon().prepareStatement(SQL)) {
			selectStatement.setString(1, searchstring+"%");
			ResultSet rs = selectStatement.executeQuery();
			list = createResultList( rs);
		} catch (SQLException e) {
			e.printStackTrace();//log
		}
		return list;
	}

	private List<Student> createResultList( ResultSet rs) throws SQLException {
		ArrayList<Student> list = new ArrayList<>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String matrNummer = rs.getString("matrikelnummer");
			String vorname = rs.getString("vorname");
			String nachname = rs.getString("nachname");
			Date geburtsdatum = rs.getDate("geburtsdatum");
			String bild = rs.getString("bild");
			list.add(new Student(id, matrNummer, vorname, nachname, geburtsdatum.toLocalDate(), bild));
		}
		return list;
	}

}
