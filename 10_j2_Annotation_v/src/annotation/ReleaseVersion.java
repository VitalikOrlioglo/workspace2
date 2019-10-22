package annotation;

//import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Target(TYPE)
@Retention(RetentionPolicy.RUNTIME) // zur laufzeit lesbar
public @interface ReleaseVersion {
	String value(); // @ReleaseVersion("0.4") - value is default
}
