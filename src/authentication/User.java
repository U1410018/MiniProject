package authentication;

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

	public File getUser_File() {
		return user_file;
	}

	public User(String first_name, String last_name, String user_name, String password) {
		setFirst_name(first_name);
		setLast_name(last_name);
		setUser_name(user_name);
		setPassword(password);
		user_file = new File("src/users/" + user_name + ".log");
		System.out.println(user_file.getAbsoluteFile());
		if (!user_file.exists()) {
			try {
				user_file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println(user_file.toString());
		}

	}

	public OutputStreamWriter getWriter() throws FileNotFoundException {
		FileOutputStream stream = new FileOutputStream(user_file, false);
		return new OutputStreamWriter(stream);
	}

	public InputStreamReader getReader() throws FileNotFoundException {
		FileInputStream stream = new FileInputStream(user_file);
		return new InputStreamReader(stream);
	}

	public static InputStreamReader getReader(String name) throws FileNotFoundException {
		File ufile = new File("src/users/" + name + ".log");
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

	@SuppressWarnings("finally")
	public static User getUserFromFile(String username, String password){
		try {
		EnDe_crypter enc = new EnDe_crypter(password);
		String s = "";
		Scanner scan = new Scanner(getUserFile(username));
		while (scan.hasNextLine()) {
			s += scan.nextLine();
		}
		scan.close();
		JSONObject object = new JSONObject(s);
		String enc_data = object.getString("enc_data");
		byte[] iv = new BASE64Decoder().decodeBuffer(object.getString("iv"));
		System.out.println("dec: "+iv);
		JSONObject obj = new JSONObject(enc.decrypt(enc_data, iv));
		String first_name = obj.getString("first_name");
		String last_name = obj.getString("last_name");
		String user_name = obj.getString("user_name");
		String balance = obj.getString("balance");

		User u = new User(first_name, last_name,(user_name), password);
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
		return String.format("%s %s %f", getFullName(), getUser_name(), getBalance());
	}
	public JSONObject getUserInfoJsonObject() throws Exception {
		JSONObject obj = new JSONObject();
		EnDe_crypter enc = new EnDe_crypter(getPassword());
		obj.put("first_name", (getFirst_name()));
		obj.put("last_name", (getLast_name()));
		obj.put("user_name", (getUser_name()));
		obj.put("balance", (String.valueOf(getBalance())));
		
		String enc_data = enc.encrypt(obj.toString());
		
		JSONObject object = new JSONObject();
		object.put("enc_data", enc_data);
		object.put("iv", new BASE64Encoder().encode(enc.getIv()));
		//		System.out.println("as: "+enc.getIv().toString());
		//		obj.put("iv", enc.getIv().toString());

		return object;
	}

	public static String getCurrentDateAndTimeString() {
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		String result = String.format("%d-%d-%d %d:%d:%d", date.getYear() + 1900, date.getMonth() + 1,
				date.getDay() + 10, date.getHours(), date.getMinutes(), date.getSeconds());
		return result;
	}
}
