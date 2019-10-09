package dao;

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
		List<Student> personList = new ArrayList<>();
		try {
			Statement selectStatement = dbconnect.getCon().createStatement();
			ResultSet rs = selectStatement.executeQuery("SELECT * FROM student");
			while (rs.next()) {
//				int id = rs.getInt("id");
				String matrikelnummer = rs.getString("matrikelnummer");
                String vorname = rs.getString("vorname");
                String nachname = rs.getString("nachname");
                Student student = new Student(matrikelnummer, vorname, nachname);
                personList.add(student);
				
				System.out.printf("%s - %s - %s\n", matrikelnummer, vorname, nachname);
			}
			System.out.println("************************************************************");
			selectStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personList;
	}

	@Override
	public boolean deleteStudent(int id) {
		return false;
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
