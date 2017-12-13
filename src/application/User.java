package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;
import encdec.EnDe_crypter;

public class User {
	private String first_name;
	private String last_name;
	private String user_name;
	private String password;
	private float balance = 0;
	private File user_file;
	private JSONArray logs = new JSONArray();
	
	public File getUser_File() {
		return user_file;
	}
	
	private User() {
		 		
	}
	public static User getInstance() {
		return new User();
	}
	public boolean created = false;
	public User(String first_name, String last_name, String user_name, String password) {
		setFirst_name(first_name);
		setLast_name(last_name);
		setUser_name(user_name);
		setPassword(password);
		
		user_file = new File("src/users/" + user_name + ".log");

		if (!user_file.exists()) {
			if (Config.createFile(user_file)) {
 				JSONObject object = new JSONObject();
 				try {
 					object.put("username", user_name);
 					object.put("password", EnDe_crypter.hash256(password, user_name));
 					addUser(user_name, object);
 				} catch (Exception e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
 				
			}
			created = true;
		}else created = false;
		
		OutputStreamWriter r;
		
		try {
			r = User.getWriter(this.getUser_File(), false);
			r.write(this.getUserInfoJsonObject().toString());
			r.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			created = false;
		}
	}
	
	public static JSONArray getUsersJsonArrayObject() throws JSONException {
		 
 		return new JSONArray(readFile(Config.getTempFile()));
 	}
 
 	public static boolean userIsExists(String username, String password) {
 		try {
 			JSONArray array = getUsersJsonArrayObject();
 			for(int i=0;i<array.length();i++) {
 				JSONObject object = array.getJSONObject(i);
 				if(object.getString("username").equals(username)) {
 					if(object.getString("password").equals(EnDe_crypter.hash256(password, username))) {
 						return true; 
 					}
 				}
 			}
 		} catch (Exception e) {
 			//System.out.println("error: " + e.getMessage());
 		}
 
 		return false;
 	}
 	
 	private boolean addUser(String username, JSONObject object) {
 		JSONArray array = null;
 		try {
 			array = getUsersJsonArrayObject();
 			array.put(object);
 			writeToFile(Config.getTempFile(), array.toString());
 			return true;
 		} catch (Exception e) {
 			System.out.println("no json object: " + e.getMessage());
 			array = new JSONArray();
 			try {
 				array.put(object);
 				writeToFile(Config.getTempFile(), array.toString());
 				return true;
 			} catch (Exception ex) {
 				ex.printStackTrace();
 			}
 		}
 
 		return false;
 	}
 	public boolean changePassword(String old_password, String new_password) {
 		try {
 			JSONArray array = getUsersJsonArrayObject();
 			for(int i=0;i<array.length();i++) {
 				JSONObject object = array.getJSONObject(i);
 				if(object.getString("username").equals(getUser_name())) {
 					if(object.getString("password").equals(EnDe_crypter.hash256(old_password, user_name))) {
 						User u = getUserFromFile(user_name, old_password);
 						u.setPassword(old_password);
 						JSONObject o = u.getUserInfoJsonObject();
 						Config.deleteUserLogsFile(getUser_name());
 						writeToFile(Config.getUserLogsFile(getUser_name()), o.toString());
 						
 						object.put("password", EnDe_crypter.hash256(new_password, user_name));
 						array.remove(i);
 						array.put(object);
 						File f = getUser_File();
 						writeToFile(Config.getTempFile(), array.toString());
 						return true; 
 					}
 				}
 			}
 		} catch (Exception e) {
 			System.out.println("error: " + e.getMessage());
 		}
 
 		return false;
 	}
 	public boolean deleteCurrentUser() {
 		try {
 			JSONArray array = getUsersJsonArrayObject();
 			for(int i=0;i<array.length();i++) {
 				JSONObject object = array.getJSONObject(i);
 				if(object.getString("username").equals(this.user_name)) {
 					array.remove(i);
 					writeToFile(Config.getTempFile(), array.toString());
 					if(clearUserData()) {
 						System.out.println("user deleted: " + user_name);
 					}else {
 						System.out.println("error on deleting user account");
 					}
 					return true;
 				}
 			}
 			return false;
 		} catch (Exception e) {
 			e.printStackTrace();
			return false;
 		}
 	}
 	private boolean clearUserData() {
 		return getUserFile(user_name).delete();
 	}
 	public static boolean writeToFile(File file, String data) {
 		try {
 			OutputStreamWriter writer = getWriter(file, false);
 			writer.write(data);
 			writer.close();
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 
 		return false;
 	}
 
 	public static OutputStreamWriter getWriter(File file, boolean appendable) throws FileNotFoundException {
 		return new OutputStreamWriter(new FileOutputStream(file, appendable));
 	}
  
 	public static InputStreamReader getreader(File file) throws FileNotFoundException {
 		return new InputStreamReader(new FileInputStream(file));
 	}

//	public OutputStreamWriter getWriter() throws FileNotFoundException {
//		FileOutputStream stream = new FileOutputStream(user_file, false);
//		return new OutputStreamWriter(stream);
//	}
	
	public static OutputStreamWriter getWriter(String name) throws FileNotFoundException {
		FileOutputStream stream = new FileOutputStream(getUserFile(name), false);
		return new OutputStreamWriter(stream);
	}

//	public InputStreamReader getReader() throws FileNotFoundException {
//		FileInputStream stream = new FileInputStream(user_file);
//		return new InputStreamReader(stream);
//	}
	
	
	public static InputStreamReader getLogReader(String name) throws FileNotFoundException {
		File ufile = new File(Config.BASE_USER_DIR + "/" + name + ".log");
		if (ufile.exists()) {
			FileInputStream stream = new FileInputStream(ufile);
			return new InputStreamReader(stream);
		}
		return null;
	}

	public static File getUserFile(String username) {
		File ufile = new File("src/users/" + username + ".log");
		return ufile;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getFullName() {
		return String.format("%s %s", getFirst_name(), getLast_name());
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}


	public static String readFile(File file) {
		String s = "";
		Scanner scan;
 		try {
 			scan = new Scanner(file);
 
 			while (scan.hasNextLine()) {
 				s += scan.nextLine();
 			}
 			scan.close();
 		} catch (FileNotFoundException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
  		}
 		return s;
	}
	
	public static User getUserFromFile(String username, String password){
		try {
			EnDe_crypter enc = new EnDe_crypter(password);
			String s = "";
			Scanner scan = new Scanner(getUserFile(username));
			while (scan.hasNextLine()) {
				s += scan.nextLine();
			}
			scan.close();
			
			JSONObject enc_data = new JSONObject(s);
			
			byte[] fn_iv = new BASE64Decoder().decodeBuffer(enc_data.getString("fn_iv"));
			byte[] ln_iv = new BASE64Decoder().decodeBuffer(enc_data.getString("ln_iv"));
			byte[] un_iv = new BASE64Decoder().decodeBuffer(enc_data.getString("un_iv"));
			byte[] b_iv = new BASE64Decoder().decodeBuffer(enc_data.getString("b_iv"));
	//		byte[] logs_iv = new BASE64Decoder().decodeBuffer(enc_data.getString("logs_iv"));
	
			String first_name = enc.decrypt(enc_data.getString("first_name"), fn_iv); 
			String last_name = enc.decrypt(enc_data.getString("last_name"), ln_iv);
			String user_name = enc.decrypt(enc_data.getString("user_name"), un_iv);
			String balance = enc.decrypt(enc_data.getString("balance"), b_iv);
	//		setLogs(enc.decrypt(enc_data.getJSONArray("logs").toString(), logs_iv));
			
			User u = new User(first_name, last_name, user_name, password);
			// TODO: decrypt the logs 
			
			u.setLogs(enc_data.getJSONArray("logs"));
			
			
			u.setBalance(Float.valueOf(balance));
	//		User u = null;
			return u;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			System.out.println("json error null");
			e.printStackTrace();
			return null;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("file not found null");
			e.printStackTrace();
			return null;
		}
		//		return null;
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public String toString() {
		return String.format("%s %s %.2f", getFullName(), getUser_name(), getBalance());
	}
	public JSONObject getUserInfoJsonObject() throws Exception {
		JSONObject obj = new JSONObject();
		EnDe_crypter enc = new EnDe_crypter(getPassword());
		obj.put("first_name", enc.encrypt(getFirst_name()));
		obj.put("fn_iv", new BASE64Encoder().encode(enc.getIv()));
		obj.put("last_name", enc.encrypt(getLast_name()));
		obj.put("ln_iv", new BASE64Encoder().encode(enc.getIv()));
		obj.put("user_name", enc.encrypt(getUser_name()));
		obj.put("un_iv", new BASE64Encoder().encode(enc.getIv()));
		obj.put("balance", enc.encrypt(String.valueOf(getBalance())));
		obj.put("b_iv", new BASE64Encoder().encode(enc.getIv()));
		// TODO: encrypt the logs
		
		obj.put("logs", getLogs());
		
		return obj;
	}

	public static String getCurrentDateAndTimeString() {
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		String result = String.format("%d-%d-%d %d:%d:%d", date.getYear() + 1900, date.getMonth() + 1,
				date.getDay() + 10, date.getHours(), date.getMinutes(), date.getSeconds());
		return result;
	}

	public JSONArray getLogs() {
		return logs;
	}

	public void setLogs(JSONArray logs) {
		this.logs = logs;
	}
}
