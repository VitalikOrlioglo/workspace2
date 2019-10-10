package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
public static void copyToUserImages(File source) {
		try {
			Path p =Files.copy(source.toPath(),new File(Config.USER_IMAGE_PATH+source.getName()).toPath());
			System.out.println(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void createUserDir() {
		File file = new File(Config.USER_IMAGE_PATH);
		
		if(!file.exists()) {
			
			file.mkdir();
		}else {
			System.out.println("Bilderordner schon vorhanden!");
		}
	
	}
	
	
	public static void main(String[] args) {
		
		createUserDir();
		System.getenv("APPDATA");
	
		
	}
}
