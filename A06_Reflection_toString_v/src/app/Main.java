package app;

import java.lang.reflect.Method;
import java.time.LocalDate;


public class Main {
	public static void printMethods(Class<?> clazz) {
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			System.out.println(method.getName());
		}
	}

	public static void main(String[] args) {
		Student s = new Student(2, "Max", "Meier", LocalDate.now());
//		printMethods(Student.class);
		System.out.println(s.toString());
		
	}

}
