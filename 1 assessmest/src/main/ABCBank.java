package main;

import java.util.ArrayList;
import java.util.Scanner;

public class ABCBank {
	class Client {
		protected String name;
		protected double balance = 0.0;
		
		public Client(String clientName, double depositAmount) {
			name = clientName;
			balance = depositAmount;
		}
		
		public Client(String clientName) {
			this(clientName, 0.0);
		}
		
		public String getName() {
			return name;
		}
		
		public Client deposit(double amount) {
			balance += amount;
			
			return this;
		}
		
		public double getBalance() {
			return balance;
		}
	}
	
	protected ArrayList<ABCBank.Client> staff;
	
	public ABCBank() {
		staff = new ArrayList<ABCBank.Client>();
	}

	public static void main(String[] args) {

		System.out.println("");
		new ABCBank().run();
	}
	
	public ABCBank deposit(String clientName, int terms, double depositAmount) {
		double intrest = calculateInterest(depositAmount, terms);
		String line = "-".repeat(42);
		
		Client client = getOrMakeClient(clientName).deposit(depositAmount).deposit(intrest);
		
		System.out.printf(
				"\n" + line + "\n\n" +
				centerText("ABCBank", 42) + "\n\n" +
			    centerText("Return on Bank Term Deposits", 42) + "\n\n" +
			    line + "\n\n" +
			    "Client: %s\n" +
			    "Deposit Amount: %.2f\n" +
			    "Term: %s\n" +
			    "Interest Earned: $%.2f\n" +
			    "Final Balance: $%.2f\n\n" +
			    line,
			    clientName, depositAmount, terms, intrest, client.getBalance()
		);
		
		return this;
	}
	
	protected Client getOrMakeClient(String name) {
		if(staffExists(name)) {
			for (int i = 0; i < staff.size(); i++) {
				Client client = staff.get(i);
				
				if(client.getName().equals(name)) return client;
			}
		}
		
		Client client = new Client(name);
		staff.add(client);
		
		return client;
	}
	
	protected boolean staffExists(String name) {
		for (int i = 0; i < staff.size(); i++) {
			Client client = staff.get(i);
			
			if(client.getName().equals(name)) return true;
		}
		
		
		return false;
	}
	
	protected double calculateInterest(double amount, int terms) {
		double intrest = 0.0;
		
		if(amount < 1000) intrest = amount * 0.02;
		else if(amount > 1000 && amount <= 5000) intrest = amount * 0.025;
		else if(amount > 5000 && amount <= 10000) intrest = amount * 0.03;
		else if(amount > 10000 && amount <= 20000) intrest = amount * 0.035;
		else intrest = amount * 0.04;
		
		return intrest * (Double.valueOf(terms) / 12d);
	}
	
	public void run() {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.print("Enter client's name: ");
		String clientName = keyboard.nextLine();
		
		System.out.print("Enter deposit amount: ");
		double depositAmount = keyboard.nextDouble();
		
		System.out.print("Enter terms (in months): ");
		int terms = keyboard.nextInt();
		
		keyboard.close();
		
		deposit(clientName, terms, depositAmount);
	}
	
	protected String centerText(String str, int width) {
		return String.format(
				"%-" + width  + "s",
				String.format("%" + (str.length() + (width - str.length()) / 2) + "s", str)
		);
	}
}






















