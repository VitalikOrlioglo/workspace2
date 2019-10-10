package util;

/*
 * System.getenv("APPDATA") -> C:Users
 */
public class Config {
	private static final String DEFAULT_IMG = "/img/default_user.png";
	public static final String USER_IMAGE_FOLDER = "StudentUserImages";
	public static final String USER_IMAGE_PATH = System.getenv("APPDATA")+"/"+USER_IMAGE_FOLDER+"/";//System.getProperty("user.home");
}
