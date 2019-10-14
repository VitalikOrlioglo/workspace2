package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Student;
import util.Config;

public class StudentDummyDAOImpl implements StudentDAO {

	@Override
	public List<Student> findAll() {
		ArrayList<Student> list = new ArrayList<>();
		
		list.add(new Student(1, "MXX0000", "Max", "Meier", LocalDate.now(), ""));
		list.add(new Student(2, "MXX0001", "Marta", "Schultz", LocalDate.now(), ""));
		list.add(new Student(3, "MXX0002", "Otto", "Lehman", LocalDate.now(), ""));
		return list;
	}

	@Override
	public boolean deleteStudent(int id) {
		return true;
	}

	@Override
	public boolean save(Student student) {
		return true;
	}

	@Override
	public boolean updateStudent(int id, String fieldName, String newValue) {
		return true;
	}

	@Override
	public List<Student> findByField(String fieldname, String searchstring) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
