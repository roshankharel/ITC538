package main;

import java.util.Scanner;

public class ABCBank {
	protected Scanner keyboard;

	public ABCBank() {
		keyboard = new Scanner(System.in);
	}

	public static void main(String[] args) {
		new ABCBank().ready();
	}

	public ABCBank deposit(String clientName, int terms, double depositAmount) {
		double interest = calculateInterest(depositAmount, terms);
		String horizontalDashedLine = "-".repeat(42);

		double totalBalance = depositAmount + interest;

		System.out.printf(
				"\n" + horizontalDashedLine + "\n\n" + centerText("ABCBank", 42) + "\n\n"
						+ centerText("Return on Bank Term Deposits", 42) + "\n\n" + horizontalDashedLine + "\n\n"
						+ "Client: %s\n" + "Deposit Amount: $%.2f\n" + "Term: %s Month" + (terms > 1 ? "s" : "") + "\n"
						+ "Interest Earned: $%.2f\n" + "Final Balance: $%.2f\n\n" + horizontalDashedLine,
				clientName, depositAmount, terms, interest, totalBalance);

		return this;
	}

	protected double calculateInterest(double amount, int terms) {
		double interest = 0.0;

		if (amount < 1000)
			interest = amount * 0.02;
		else if (amount > 1000 && amount <= 5000)
			interest = amount * 0.025;
		else if (amount > 5000 && amount <= 10000)
			interest = amount * 0.03;
		else if (amount > 10000 && amount <= 20000)
			interest = amount * 0.035;
		else
			interest = amount * 0.04;

		return interest * (Double.valueOf(terms) / 12d);
	}

	public void ready() {
		while (true) {
			System.out.println();

			run();

			System.out.print("\n\nCalculate interest for another client? [Yes / No (any key)]: ");

			String keepGoing = keyboard.nextLine().strip().toLowerCase();

			System.out.println();

			if (keepGoing.equals("yes") || keepGoing.equals("y"))
				continue;

			break;
		}

		keyboard.close();
	}

	public void run() {
		String clientName = askClientName();
		double depositAmount = askDepositeAmount();
		int terms = askNumberOfTerms();

		deposit(clientName, terms, depositAmount);
	}

	protected String askClientName() {
		do {
			System.out.print("Enter client's name: ");
			String clientName = keyboard.nextLine();

			if (clientName.strip().length() > 0)
				return clientName;

			System.out.println("Error: Client's name should at least one character.");
		} while (true);
	}

	protected double askDepositeAmount() {
		do {
			System.out.print("Enter deposit amount: ");

			try {
				double depositAmount = Double.valueOf(keyboard.nextLine());

				if (depositAmount > 0)
					return depositAmount;

				System.out.println("Error: Deposite amount should be a greater than 0 (zero).");
			} catch (NumberFormatException e) {
				System.out.println("Error: Deposite amount should be a valid number.");
			}
		} while (true);
	}

	protected int askNumberOfTerms() {
		do {
			System.out.print("Enter terms (in months): ");

			try {
				int terms = Integer.valueOf(keyboard.nextLine());

				if (terms > 0)
					return terms;

				System.out.println("Error: Terms must be greater than 0 (zero).");
			} catch (NumberFormatException e) {
				System.out.println("Error: Terms must be a valid integer.");
			}
		} while (true);
	}

	protected String centerText(String text, int width) {
		return String.format("%-" + width + "s",
				String.format("%" + (text.length() + (width - text.length()) / 2) + "s", text));
	}
}
