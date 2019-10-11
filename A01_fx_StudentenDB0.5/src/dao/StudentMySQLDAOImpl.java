package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBConnect;
import db.DBConnectException;
import model.Student;

public class StudentMySQLDAOImpl implements StudentDAO {
	private DBConnect dbconnect;
	
	public StudentMySQLDAOImpl() throws DBConnectException {
		dbconnect = DBConnect.getIntance();
	}
	@Override
	public List<Student> findAll() {
		ArrayList<Student> list = new ArrayList<>();
		try {
			PreparedStatement selectStatement = dbconnect.getCon().prepareStatement("SELECT * FROM student");
			ResultSet rs = selectStatement.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String matrNummer = rs.getString("matrikelnummer");
				String vorname = rs.getString("vorname");
				String nachname = rs.getString("nachname");
				Date geburtsdatum = rs.getDate("geburtsdatum");
				String bild = rs.getString("bild");
				list.add(new Student(id, matrNummer, vorname, nachname, geburtsdatum.toLocalDate(), bild));
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
			return deletePreparedStatement.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean save(Student student) {
		try {
			PreparedStatement ps = dbconnect.getCon().prepareStatement("INSERT INTO student (matrikelnummer,vorname, nachname, geburtsdatum,bild)"
					+ "VALUES (?,?,?,?,?)");
			ps.setString(1, student.getMatrikelnummer());
			ps.setString(2, student.getVorname());
			ps.setString(3, student.getNachname());
			ps.setDate(4, Date.valueOf(student.getGeburtsdatum()));  //convert Localdate to sql.Date
			ps.setString(5, student.getBild());
			return ps.executeUpdate()==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateStudent(int id, String fieldName, String newValue) {
		PreparedStatement updatePreparedStatement;
		try {
			updatePreparedStatement = dbconnect.getCon()
					.prepareStatement("UPDATE student SET " + fieldName +" =? WHERE id=?");
			updatePreparedStatement.setString(1, newValue);
			updatePreparedStatement.setInt(2, id);
			return updatePreparedStatement.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
//		StudentMySQLDAOImpl s = new StudentMySQLDAOImpl();
//		System.out.println(s.findAll());

	}

}
