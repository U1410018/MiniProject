package application;

import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

// Distance between dreams and reality is called actions

public class Actions {
	private String date;
	private User action_performer_user;
	private float balance_after_action = -1;
	private float amount_of_money_in_action = -1;
	int action_type = 0;
	
	public Actions(int action, User user) {
		action_type = action;
		this.action_performer_user = user;
		//perform();
	}
	
	public String getActionString(int action) {
		switch (action) {
		case Action.DEP:
			return "DEPOSIT";
		case Action.WITH:
			return "WITHDRAW";
		case Action.TRA:
			return "TRANSFER";
		default:
			return null;
		}
	}
	
	public void perform() {
		setDate(User.getCurrentDateAndTimeString());
		switch (action_type) {
		case Action.DEP:
			
			if(amount_of_money_in_action > 0) {
			setBalance_after_action(action_performer_user.getBalance() + amount_of_money_in_action);
			
//			try {
//				OutputStreamWriter r = User.getWriter(action_performer_user.getUser_name());
//				r.close();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			}else {
				System.out.println("Enter deposit amount to perform your action");
			}
			break;
		case Action.WITH:
			
			if(amount_of_money_in_action>0) {
				setBalance_after_action(action_performer_user.getBalance() - amount_of_money_in_action);
			}else {
				System.out.println("Enter withdraw amount to perform your action");
			}
			break;
		case Action.TRA:
			
			break;
		default:
			break;
		}
		
	}
	
	public User getActionPerformerUser() {
		return action_performer_user;
	}
	
	public float getBalance_after_action() {
		return balance_after_action;
	}

	public void setBalance_after_action(float balance_after_action) {
		this.balance_after_action = balance_after_action;
	}
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	public float getAmount_of_money_in_action() {
		return amount_of_money_in_action;
	}

	public void setAmount_of_money_in_action(float amount_of_money_in_action) {
		this.amount_of_money_in_action = amount_of_money_in_action;
	}
	interface Action{
		int DEP = 100;
		int WITH = 101;
		int TRA = 102;
	}
}
