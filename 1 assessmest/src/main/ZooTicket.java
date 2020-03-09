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
		int numberOfAccompaniedChildren = getNumberOfAccompaniedChildren(children, adults, seniors);
		int numberOfUnaccompaniedChildren = getNumberOfUnaccompaniedChildren(children, adults, seniors);
		
		
		int accompaniedChildrenCost = calculateAccompaniedChildrenCost(numberOfAccompaniedChildren);
		int unacompaniedChildrenCost = calculateUnaccompaniedChildrenCost(numberOfUnaccompaniedChildren);
		int adultCost = calculateAdultCost(adults);
		int seniorCost = calculateSeniorCost(seniors);
		
		int groupCharge = accompaniedChildrenCost + unacompaniedChildrenCost + adultCost + seniorCost;
		
		totalCharge += groupCharge;
		
		System.out.printf("Total entry charge is $%d\n\n", groupCharge);
	}
	
	protected int calculateAccompaniedChildrenCost(int count) {
		return count * ACCOMPANIED_CHILDREN_COST;
	}

	protected int calculateUnaccompaniedChildrenCost(int count) {
		return count * UNACCOMPANIED_CHILDREN_COST;
	}
	
	protected int calculateAdultCost(int count) {
		return count * ADULT_COST;
	}
	
	protected int calculateSeniorCost(int count) {
		return count * SENIOR_COST;
	}
	
	protected int getNumberOfAccompaniedChildren(int children, int adults, int seniors) {
		return children > adults + seniors ? children - adults - seniors : children;
	}
	
	protected int getNumberOfUnaccompaniedChildren(int children, int adults, int seniors) {
		return children - getNumberOfAccompaniedChildren(children, adults, seniors);
	}
}
