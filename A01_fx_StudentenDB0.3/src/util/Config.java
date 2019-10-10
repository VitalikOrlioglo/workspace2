package util;

public class Config {
	
	public static final String DEFAULT_IMG = "/img/default_user.png";// von Packages-Root-> getResorces...

	public static final String USER_IMAGE_FOLDER = "StudentUserImages";
	public static final String USER_IMAGE_PATH = System.getenv("APPDATA")+"/"+USER_IMAGE_FOLDER+"/";//System.getProperty("user.home");
}
