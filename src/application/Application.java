package application;

import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import application.Actions.Action;

public class Application {
	
	public static void writeActionToFile(Actions a, User u) {
		try {
			
			JSONObject action = new JSONObject();
			action.put("Date", a.getDate());
			action.put("Amount", a.getAmount_of_money_in_action());
			action.put("Balance", a.getBalance_after_action());
			action.put("Action", a.getActionString(a.action_type));
			JSONArray arr = u.getLogs();
			arr.put(action);
			u.setLogs(arr);
			String fileContent = "";
			Scanner scan = new Scanner(User.getUserFile(u.getUser_name()));
			while (scan.hasNextLine()) {
				fileContent += scan.nextLine();
			}
			scan.close();
			
			JSONObject jsn = new JSONObject(fileContent);
			jsn.put("logs", u.getLogs());
			
			OutputStreamWriter r;
			r = User.getWriter(u.getUser_File(), false);
			System.out.println("JSN:blablabl " + jsn.toString());
			r.write(jsn.toString());
			r.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		Config.initialize();
		
		User u2 = new User("Umar", "Kholdarbekov", "12174152", "Admin1234");
		User u3 = new User("Bunyod", "Ibrokhimov", "12174117", "hello1234");
		User u1 = new User("Muhammad", "Tokhirov", "12174169", "Pa$$w0rd");
		
//		String fileContent = "";
//		Scanner scan;
//		try {
//			scan = new Scanner(User.getUserFile(u1.getUser_name()));
//			while (scan.hasNextLine()) {
//				fileContent += scan.nextLine();
//			}
//			System.out.println("File contents in main: " + fileContent);
//			scan.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		User user1 = User.getUserFromFile("12174152", "Admin1234");
		System.out.println(user1.toString());
		User user2 = User.getUserFromFile("12174117", "hello1234");
		System.out.println(user2.toString());
		User user = User.getUserFromFile("12174169", "Pa$$w0rd");
		System.out.println(user.toString());
		
		Actions ac = new Actions(Action.DEP, user1);
		ac.setAmount_of_money_in_action(666);
		ac.perform();
		writeActionToFile(ac, user1);
		
		System.out.println("password change: " + user.changePassword("123", "1234"));
		//		user.deleteCurrentUser("Pa$$w0rd");
//		System.out.println(user.toString());
//		user = User.getUserFromFile("12174152", "Admin1234");
//		System.out.println(user.toString());
//		user = User.getUserFromFile("12174117", "hello1234");
//		System.out.println(user.toString());
		
		System.out.println("is_exists: "+User.userIsExists("12174169", "1234"));
		
	}
	
}
