package dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DBConnect;
import model.Student;

public class StudentMySQLDAOImpl implements StudentDAO{
	
	private DBConnect dbconnect = DBConnect.getIntance(); 

	@Override
	public List<Student> findAll() {
		try {
			Statement selectStatement =dbconnect.getCon().createStatement();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteStudent(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(Student student) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateStudent(int id, String fieldName, String newValue) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static void main(String[] args) {
		StudentMySQLDAOImpl s = new StudentMySQLDAOImpl();
		System.out.println(s.findAll());
		
	}

}
