package main;

import java.util.Scanner;

/**
* The class provides a command line interface to ABCBank employees
* to calculate interest and total balance of a client for
* a given deposit in fixed terms
*
* @version 0.1
* @author Roshan Kharel, 11691041
*/
public class ABCBank {
	protected Scanner keyboard;

	public static void main(String[] args) {
		new ABCBank().ready();
	}
	
	public ABCBank() {
		keyboard = new Scanner(System.in);
	}

	/**
    * Method to allow user to calculate multiple clients' interest interactively
    */
	public void ready() {
		while (true) {
			run();

			System.out.print("\nCalculate interest for another client? [Yes (Y or Yes) / No (any key)]: ");

			String keepGoing = keyboard.nextLine().strip().toLowerCase();

			System.out.println();

			if (keepGoing.equals("yes") || keepGoing.equals("y"))
				continue;

			break;
		}

		keyboard.close();
	}

	/**
    * Method to allow user to calculate single client's interest interactively
    */
	public void run() {
		String horizontalDashedLine = "-".repeat(50);

		System.out.printf(
				horizontalDashedLine + "\n"
				+ centerText("ABCBank", 50) + "\n"
				+ centerText("Enter client's details to calulate the intrest.", 50) + "\n"
				+ horizontalDashedLine + "\n\n"
		);

		String clientName = askClientName();
		double depositAmount = askdepositAmount();
		int terms = askNumberOfTerms();

		deposit(clientName, terms, depositAmount);
	}

	/**
	* Interactively prompts for client's name
	*
	* @return Client's name
	*/
	protected String askClientName() {
		do {
			System.out.printf("Enter client's name: ");
			String clientName = keyboard.nextLine();

			if (clientName.strip().length() > 0) {
				System.out.println();
				
				return clientName;
			}

			System.out.println("Error: Client's name should be at least single character.\n");
		} while (true);
	}

	/**
	* Interactively prompts for deposit amount
	*
	* @return Client's deposit amount
	*/
	protected double askdepositAmount() {
		do {
			System.out.printf("Enter deposit amount: ");

			try {
				double depositAmount = Double.valueOf(keyboard.nextLine());

				if (depositAmount > 0) {
					System.out.println();					
					
					return depositAmount;
				}

				System.out.println("Error: deposit amount should be a greater than 0 (zero).\n");
			} catch (NumberFormatException e) {
				System.out.println("Error: deposit amount should be a valid number.\n");
			}
		} while (true);
	}

	/**
	* Interactively prompts for number of terms in months
	*
	* @return Number of terms (in month)
	*/
	protected int askNumberOfTerms() {
		do {
			System.out.print("Enter terms (in months): ");

			try {
				int terms = Integer.valueOf(keyboard.nextLine());

				if (terms > 0) {
					System.out.println();
					
					return terms;
				}

				System.out.println("Error: Terms must be greater than 0 (zero).\n");
			} catch (NumberFormatException e) {
				System.out.println("Error: Terms must be a valid integer.\n");
			}
		} while (true);
	}

	/**
    * Calculate and display interest earned, initial deposit amount,
    * final balance, and other details
    *
    * @param  clientName			the name of the client
    * @param  terms						number of months to deposit
    * @param  depositAmount		client's deposit amount
    *
		* @return	ABCBank instance (for method chaining)
    */
	public ABCBank deposit(String clientName, int terms, double depositAmount) {
		double interest = calculateInterest(depositAmount, terms);
		String horizontalDashedLine = "-".repeat(50);

		double finalBalance = depositAmount + interest;

		System.out.printf(
				horizontalDashedLine + "\n"
				+ centerText("ABCBank", 50) + "\n"
				+ centerText("Return on Bank Term Deposits", 50) + "\n"
				+ horizontalDashedLine + "\n\n"
				+ "Client: %s\n"
				+ "Deposit Amount: $%.2f\n"
				+ "Term: %s Month" + (terms > 1 ? "s" : "") + "\n"
				+ "Interest Earned: $%.2f\n"
				+ "Final Balance: $%.2f\n\n"
				+ horizontalDashedLine + "\n",
			clientName, depositAmount, terms, interest, finalBalance);

		return this;
	}

	/**
    * Calculate interest that the client can earn at the end of terms of deposit
    *
    * @param  amount	the client's deposit amount
    * @param  terms		duration (in months) of fixed deposit
    *
		* @return	interest earned by client
    */
	protected double calculateInterest(double amount, int terms) {
		double interest = 0.0;
		double years = Double.valueOf(terms) / 12d;

		if (amount <= 1000) {
			interest = amount * 0.02;
		} else if (amount > 1000 && amount <= 5000) {
			interest = amount * 0.025;
		} else if (amount > 5000 && amount <= 10000) {
			interest = amount * 0.03;
		} else if (amount > 10000 && amount <= 20000) {
			interest = amount * 0.035;
		} else {
			interest = amount * 0.04;
		}

		return interest * years;
	}

	/**
	* Utility method to center a given text by adding whitespace
	*
	* @param text		the text to be padded with whitespace
	* @param width	the length of the characters in which text is to be centered
	*
	* @return 			text padded with whitespace
	*/
	protected String centerText(String text, int width) {
		return String.format(
			"%-" + width + "s",
			String.format(
				"%" + (text.length() + (width - text.length()) / 2) + "s",
				text
			)
		);
	}
}
