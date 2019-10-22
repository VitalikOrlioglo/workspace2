package annotation;

import java.lang.reflect.Method;

@ReleaseVersion("v0.1")
public class AnnotationTest {

	
	@CodeModified(autor = "Max", date = "12.12.2012", bugfixes = "fix . . .")
	public void m1() {
		
	}
	
	@CodeModified(autor = "BAran", date = "21.10.2019")
	public void m2() {
		
	}
	
	@NotUsed
	public void x() {
		
	}
	
	public static void main(String[] args) {
		// Jeder annotation ist ein Datentyp
		ReleaseVersion rv = AnnotationTest.class.getAnnotation(ReleaseVersion.class);
		if (rv!=null) {
			System.out.println(rv.value());
		}
		
		// @CodeModified
		Method[] methods = AnnotationTest.class.getMethods();
		for (Method method : methods) {
			CodeModified cm = method.getAnnotation(CodeModified.class);
			if (cm!=null) {
				System.out.printf("Methode %s: Entwickler %s, Datum %s, Fix %s\n",
						method.getName(),
						cm.autor(),
						cm.date(),
						cm.bugfixes()
						);
			}
		}
	}
}
