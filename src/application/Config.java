package application;

import java.io.File;

public class Config {
	public static String BASE = "src";
	public static String BASE_USER_FILE = BASE+"/users";
	public static void initialize() {
		File file = new File(BASE_USER_FILE);
		if(!file.exists()) {
			file.mkdir();
		}
		//google
	}
}
