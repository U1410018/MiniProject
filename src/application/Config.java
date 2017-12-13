package application;

import java.io.File;
import java.io.IOException;

import org.json.JSONObject;

public class Config {
	public static String BASE = "src";
	public static String BASE_USER_DIR = BASE + "/users";
	public static String BASE_USER_INFOS = BASE + "/temps";
	public static String TEMP_FILE = BASE_USER_INFOS + "/users.dat";

	public static void initialize() {
		File file = new File(BASE_USER_DIR);
		createDir(file);
		file = new File(BASE_USER_INFOS);
		createDir(file);
		file = new File(TEMP_FILE);
		createFile(file);
	}

	public static File getTransferTemps(String user_name) {
		return new File(BASE_USER_INFOS + "/" + user_name + "_tmp.dat");
	}

	public static File getTempFile() {
		return new File(TEMP_FILE);
	}

	public static File getUserLogsFile(String username) {
		return new File(BASE_USER_DIR + "/" + username + ".log");
	}

	public static boolean deleteUserLogsFile(String username) {
		try {
			File f = new File(BASE_USER_DIR + "/" + username + ".log");
			f.delete();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public static boolean createFile(File file) {
		if (!file.exists()) {
			try {
				file.createNewFile();
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public static void createDir(File file) {
		if (!file.exists()) {
			file.mkdir();
		}
	}
}
