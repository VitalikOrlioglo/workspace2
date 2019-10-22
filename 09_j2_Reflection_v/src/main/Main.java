package main;

import java.lang.reflect.Method;
import java.util.ArrayList;

import model.Person;

public class Main {
	public static void printMethods(Class<?> clazz) {
//		Method[] methods = clazz.getDeclaredMethods();
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			System.out.println(method.getName());
		}
	}
	
	/*
	 * getMethode muss:
	 * - Ruckgabe -Datentyp- deklariert (kein void) return
	 * - keine Parameter
	 * - beginnt mit get
	 */
	public static boolean isGet(Method m) {
		if (!m.getName().startsWith("get")) { // Java bean konvention: beginnt mit set
			return false;
		} if (m.getParameterTypes().length!=0) { // keine Parameter
			return false;
		} if (void.class.equals(m.getReturnType())) { // es wird void zuruckgegeben
			return false;
		}
		return true;
	}
	
	/*
	 * setMethode muss:
	 * - Ruckgabe void
	 * - Parameter hat
	 * - beginnt mit set
	 */
	public static boolean isSet(Method m) {
		if (!m.getName().startsWith("set")) { // Java bean konvention: beginnt mit set
			return false;
		} if (m.getParameterTypes().length!=1) { // muss nur ein Parameter haben
			return false;
		} if (!void.class.equals(m.getReturnType())) {
			return false;
		}
		return true;
	}
	
	public static void printClassHierarchy(Class<?> c) {
//		System.out.println(c.getSuperclass().getName());
		if ( c != null ){
			System.out.println(c.getSimpleName());
			printClassHierarchy(c.getSuperclass());
		}
	}
	
	
	public static void printClassHierarchy2(Class<?> c, int classdepth) {
//		System.out.println(c.getSuperclass().getName());
		if ( c.getSuperclass() != null ){
			System.out.println(c.getName());
			printClassHierarchy2(c.getSuperclass(), classdepth+1);
		}
	}

	
	public static Object executeMethod(Object o, String name, Class<?>[] types, Object[] values) {
		Object resultObject = null;
		try {
			Method m = o.getClass().getMethod(name, types);
			resultObject = m.invoke(o, values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultObject;
	}
	
	public static Object createObject(String className) {
		Object o = null;
		try {
			Class<?> c = Class.forName(className);
			o = c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}
	
	
	public static void main(String[] args) {
//		printMethods(Person.class);
//		printMethods(getClass());// Cannot make a static reference to the non-static method getClass() from the type Object
//		printMethods(this.getClass());// geht nicht, weill printMethods ist static - "Cannot use this in a static context"
		
//		printClassHierarchy(ArrayList.class );
		Person p = (Person) createObject("model.Person");
		
		executeMethod(p, "setName", new Class[] {String.class}, new String[] {"Max"} );
		System.out.println(p.getName());
	}

}
