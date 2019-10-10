package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Student;

/**
 * fur Testen oder spater fur Mock
 * Student
 * @author vitali orlioglo
 * 10.10.2019
 * A01_fx_StudentenDB_v
 */
public class StudentDummyDAOImpl implements StudentDAO {

	@Override
	public List<Student> findAll() {
		ArrayList<Student> list = new ArrayList<>();
		list.add(new Student(1, "dsdsfgdg", "Kaban", "Kabanova", LocalDate.now(), "/img/bild1.png"));
		list.add(new Student(1, "5454dfg", "Sican", "Tralala", LocalDate.now(), "/img/bild1.png"));
		list.add(new Student(1, "dfgd5fg744", "Patkan", "Hulala", LocalDate.now(), "/img/bild1.png"));
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
	
}
