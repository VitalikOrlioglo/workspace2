package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import db.DBConnect;
import model.Student;

public class StudentMYSQLDAOImpl implements StudentDAO {
	private DBConnect dbconnect = DBConnect.getInstance();

	@Override
	public List<Student> findAll() {
		ArrayList<Student> list = new ArrayList<>();
		try {
			PreparedStatement selectStatement = dbconnect.getCon().prepareStatement("SELECT * FROM student");
			ResultSet rs = selectStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String matrikelnummer = rs.getString("matrikelnummer");
				String vorname = rs.getString("vorname");
				String nachname = rs.getString("nachname");
				Date geburtsdatum = rs.getDate("geburtsdatum");
				String bild = rs.getString("bild");
				
				list.add(new Student(id, matrikelnummer, vorname, nachname, geburtsdatum.toLocalDate(), bild));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean deleteStudent(int id) {
		PreparedStatement deletePreparedStatement;
		try {
			deletePreparedStatement = dbconnect.getCon().prepareStatement("DELETE FROM student WHERE id=?");
			deletePreparedStatement.setInt(1, id);
			deletePreparedStatement.executeUpdate();
			System.out.println("updateCount delete: " + deletePreparedStatement.getUpdateCount());
			deletePreparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean save(Student student) {
		return false;
	}

	@Override
	public boolean updateStudent(int id, String fieldName, String newValue) {
		return false;
	}
	
	public static void main(String[] args) {
		StudentMYSQLDAOImpl s = new StudentMYSQLDAOImpl();
		System.out.println(s.findAll());
	}
	
}
