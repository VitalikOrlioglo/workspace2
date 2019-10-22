package annotation;
import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( {METHOD, TYPE, CONSTRUCTOR} )
@Retention(RetentionPolicy.RUNTIME)
public @interface CodeModified {
	
	String autor(); // pflicht
	String date(); // pflicht
	String bugfixes() default ""; // optional
}
