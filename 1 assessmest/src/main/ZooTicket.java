package main;

import java.util.Scanner;

public class ZooTicket {
	protected final int ACCOMPANIED_CHILDREN_COST = 2;
	protected final int UNACCOMPANIED_CHILDREN_COST = 5;
	protected final int ADULT_COST = 10;
	protected final int SENIOR_COST = 8;
	
	protected int totalCharge = 0;

	public static void main(String[] args) {
		new ZooTicket().run();
	}
	
	public void run() {
		boolean keepGoing = true;
		Scanner keyboard = new Scanner(System.in);
		
		do {
			System.out.print("Enter a group? (Yes=1/No=0): ");
			int group = keyboard.nextInt();
			
			if(group == 0) {
				keepGoing = false;
				showTotal();
				break;
			}
			
			System.out.print("Enter the number of children (age 6–15): ");
			int numberOfChildren = keyboard.nextInt();
			
			System.out.print("Enter the number of adults (age 16–59): ");
			int numberOfAdults = keyboard.nextInt();
			
			System.out.print("Enter the number of seniors (age 60+): ");
			int numberOfSeniors = keyboard.nextInt();
			
			showTotal(numberOfChildren, numberOfAdults, numberOfSeniors);
		} while(keepGoing);
		
		keyboard.close();
		
	}
	
	void showTotal() {
		System.out.printf("Total takings: $%d\n", totalCharge);
	}
	
	void showTotal(int children, int adults, int seniors) {
		int numberOfAccompaniedChildren = children;
		int numberOfUnaccompaniedChildren = 0;
		
		if(children > adults + seniors) {
			numberOfUnaccompaniedChildren = children - (adults + seniors);
			numberOfAccompaniedChildren -= numberOfUnaccompaniedChildren;
		}
		
		int accompaniedChildrenCost = numberOfAccompaniedChildren * ACCOMPANIED_CHILDREN_COST;
		int unacompaniedChildrenCost = numberOfUnaccompaniedChildren * UNACCOMPANIED_CHILDREN_COST;
		int adultCost = adults * ADULT_COST;
		int seniorCost = seniors * SENIOR_COST;
		
		int groupCharge = accompaniedChildrenCost + unacompaniedChildrenCost + adultCost + seniorCost;
		
		totalCharge += groupCharge;
		
		System.out.printf("Total entry charge is $%d\n\n", groupCharge);
	}

}
