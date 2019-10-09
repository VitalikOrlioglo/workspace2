package dao;

import java.util.List;

import model.Student;

public interface StudentDAO {
	
	List<Student> findAll();
	boolean deleteStudent(int id);
	boolean save(Student student);
	boolean updateStudent(int id, String fieldName, String newValue);
}
