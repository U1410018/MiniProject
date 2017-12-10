package authentication;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.Scanner;

import org.json.JSONObject;

// Mukhammadjon Tokhirov
public class Authentication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello world");
		// Umar is the best
		User u = new User("Mukhammadjon", "Tokhirov", "12174169", "Pa$$w0rd");
		try {
			OutputStreamWriter r = u.getWriter();

			r.write(u.getUserInfoJsonObject().toString());
			r.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error: "+e.getMessage());
			e.printStackTrace();
		}
		User user = User.getUserFromFile("12174169", "Pa$$w0rd");
			System.out.println(user.toString());
		
	}

}
