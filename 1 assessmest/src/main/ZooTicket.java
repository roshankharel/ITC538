package main;

import java.util.ArrayList;
import java.util.Scanner;

public class ZooTicket {
    protected final int ACCOMPANIED_CHILDREN_COST   = 2;
    protected final int UNACCOMPANIED_CHILDREN_COST = 5;
    protected final int ADULT_COST                  = 10;
    protected final int SENIOR_COST                 = 8;

    protected int       totalCharge                 = 0;
    protected Scanner   keyboard;

    public static void main(String[] args) {
        new ZooTicket().run();
    }

    public ZooTicket() {
        keyboard = new Scanner(System.in);
    }

    public void run() {
        boolean keepGoing = true;

        ArrayList<Integer> acceptedValues = new ArrayList<Integer>();
        acceptedValues.add(0);
        acceptedValues.add(1);

        String horizontalDashedLine = "-".repeat(60);

        System.out.printf(
                horizontalDashedLine + "\n"
                + centerText("Welocme to the Zoo", 60) + "\n"
                + centerText("Our ticketing price is as follows:", 60) + "\n\n"
                + "Children 5 years old and younger: free\n" + "Accompanied children from 6 to 15 years old: $2 each\n"
                + "Unaccompanied children from 6 to 15 years old: $5 each\n"
                + "Adults from 16 to 59 years old: $10 each\n" + "Seniors from 60 years and older: $8 each\n\n"
                + horizontalDashedLine + "\n\n");

        do {
            System.out.print("Enter a group? (Yes=1/no=0): ");
            int group;

            try {
                group = Integer.valueOf(keyboard.nextLine());

                if (!acceptedValues.contains(group)) {
                    System.out.println("Error: only 1 and 0 are accepted values.\n");
                    continue;
                }

                System.out.println();
            } catch (NumberFormatException e) {
                System.out.println("Error: only 1 and 0 are accepted values.\n");
                continue;
            }

            if (group == 0) {
                keepGoing = false;
                showTotal();
                break;
            }

            int numberOfChildren = askNumberOfChildren();
            int numberOfAdults = askNumberOfAdults();
            int numberOfSeniors = askNumberOfSeniors();

            showTotal(numberOfChildren, numberOfAdults, numberOfSeniors);
        } while (keepGoing);

        keyboard.close();

    }

    protected int askNumberOfChildren() {
        do {
            System.out.print("Enter the number of children (age 6–15): ");
            int numberOfChildren;

            try {
                numberOfChildren = Integer.valueOf(keyboard.nextLine());

                if (numberOfChildren < 0) {
                    System.out.println("Error: The value cannot be negative.\n");
                }

                System.out.println();

                return numberOfChildren;
            } catch (NumberFormatException e) {
                System.out.println("Error: Only integer value is accepted.\n");
            }

        } while (true);
    }

    protected int askNumberOfSeniors() {
        do {
            System.out.print("Enter the number of seniors (age 60+): ");
            int numberOfChildren;

            try {
                numberOfChildren = Integer.valueOf(keyboard.nextLine());

                if (numberOfChildren < 0) {
                    System.out.println("Error: The value cannot be negative.\n");
                }

                System.out.println();

                return numberOfChildren;
            } catch (NumberFormatException e) {
                System.out.println("Error: Only integer value is accepted.\n");
            }

        } while (true);
    }

    protected int askNumberOfAdults() {
        do {
            System.out.print("Enter the number of adults (age 16–59): ");
            int numberOfChildren;

            try {
                numberOfChildren = Integer.valueOf(keyboard.nextLine());

                if (numberOfChildren < 0) {
                    System.out.println("Error: The value cannot be negative.\n");
                }

                System.out.println();

                return numberOfChildren;
            } catch (NumberFormatException e) {
                System.out.println("Error: Only integer value is accepted.\n");
            }

        } while (true);
    }

    protected void showTotal() {
        System.out.printf("Total takings: $%d\n", totalCharge);
    }

    protected void showTotal(int children, int adults, int seniors) {
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

    /**
     * Utility method to center a given text by adding whitespace
     *
     * @param text  the text to be padded with whitespace
     * @param width the length of the characters in which text is to be centered
     *
     * @return text padded with whitespace
     */
    private String centerText(String text, int width) {
        return String.format("%-" + width + "s",
                String.format("%" + (text.length() + (width - text.length()) / 2) + "s", text));
    }
}
