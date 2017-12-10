package authentication;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.Scanner;

import org.json.JSONObject;

// Mukhammadjon Tokhirov
public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("Hello world");
		// Umar is the best
		User u1 = new User("Mukhammadjon", "Tokhirov", "12174169", "Pa$$w0rd");
		User u2 = new User("Umar", "Kholdarbekov", "12174152", "Admin1234");
		User u3 = new User("Bunyod", "Ibrokhimov", "12174117", "hello1234");
		
		User user = User.getUserFromFile("12174169", "Pa$$w0rd");
			System.out.println(user.toString());
		user = User.getUserFromFile("12174152", "Admin1234");
		System.out.println(user.toString());
		user = User.getUserFromFile("12174117", "hello1234");
		System.out.println(user.toString());
	}
	
}
