package main;

import java.util.Scanner;

/**
 * This class provides a Command Line Interface (CLI) to help calculate and
 * display the entry charge for each family group and also display the total
 * takings for a sequence of groups
 *
 * @version 0.1
 * @author Roshan Kharel, 11691041
 */
public class ZooTicket {
    protected final int ADULT_COST = 10;
    protected final int SENIOR_COST = 8;
    protected final int ACCOMPANIED_CHILDREN_COST = 2;
    protected final int UNACCOMPANIED_CHILDREN_COST = 5;

    protected int totalCharge = 0;
    protected Scanner keyboard;

    public static void main(String[] args) {
        new ZooTicket().run();
    }

    public ZooTicket() {
        keyboard = new Scanner(System.in);
    }

    /**
     * Method to run indefinitely to compute and display group entry charge and
     * total takings of all groups
     */
    public void run() {
        String horizontalDashedLine = "-".repeat(60);

        // display welcome message and pricing information
        System.out.println(
            horizontalDashedLine + "\n"
            + centerText("Welocme to the Zoo", 60) + "\n"
            + centerText("Our ticketing price is as follows:", 60) + "\n\n"
            + "Children 5 years old and younger: free\n"
            + "Accompanied children from 6 to 15 years old: $2 each\n"
            + "Unaccompanied children from 6 to 15 years old: $5 each\n"
            + "Adults from 16 to 59 years old: $10 each\n"
            + "Seniors from 60 years and older: $8 each\n\n"
            + horizontalDashedLine + "\n"
        );

        // runs untill terminated
        while(true) {
            System.out.print("Enter a group? (Yes=1/no=0): ");

            String group = keyboard.nextLine().trim();

            // check is user's choice is other than 0 and 1
            if (!group.equals("0") && !group.equals("1") ) {
                System.out.println("Error: only 1 and 0 are accepted values.\n");
                continue;
            }

            System.out.println(); // display empty line

            // selection 0 will display total takings for a sequence
            // of groups and terminates the program
            if(group.equals("0")) {
                showTotal();
                break; // terminate the loop
            }

            // the code below is automatically executed as else
            // selection 1 will calculate and display the entry charge for each family group

            int numberOfChildren = askInteger(
                "Enter the number of children (age 6-15): ",
                "number of children"
            );

            int numberOfAdults = askInteger(
                "Enter the number of adults (age 16-59): ",
                "number of adults"
            );

            int numberOfSeniors = askInteger(
                "Enter the number of seniors (age 60+): ",
                "number of seniors"
            );

            showTotal(numberOfChildren, numberOfAdults, numberOfSeniors);
        }

        keyboard.close();

    }

    /**
     * A generic method to interactively asks for an integer value
     * that is greater than or equal to zero (0)
     *
     * @param message       a input prompt message
     * @param label         name of the input
     *
     * @return an integer
     */
    protected int askInteger(String message, String label) {
        do {
            System.out.print(message);
            int userInputInteger;

            try {
                String userInput = keyboard.nextLine();
                // convert user's input to an integer
                userInputInteger = Integer.valueOf(userInput);

                if (userInputInteger < 0) {
                    System.out.printf("Error: The %s cannot be negative.\n\n", label);
                    continue;
                }

                System.out.println();

                return userInputInteger;
            } catch (NumberFormatException e) {
                System.out.printf("Error: Only integer value is accepted for %s.\n\n", label);
            }

        } while (true);
    }

    /**
     * Method to display total takings of all groups
     */
    protected void showTotal() {
        System.out.printf("Total takings: $%d\n", totalCharge);
    }

    /**
     * Computes and displays group's total entry charge
     *
     * @param childern number of children
     * @param adults   number of adults
     * @param seniors  number of seniors
     */
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

    /**
     * Calculates the costs of accompanied children
     *
     * @param count number of children
     *
     * @return entry charge for accompanied children
     */
    protected int calculateAccompaniedChildrenCost(int count) {
        return count * ACCOMPANIED_CHILDREN_COST;
    }

    /**
     * Calculates the costs of unaccompanied children
     *
     * @param count number of children
     *
     * @return entry charge for unaccompanied children
     */
    protected int calculateUnaccompaniedChildrenCost(int count) {
        return count * UNACCOMPANIED_CHILDREN_COST;
    }

    /**
     * Calculates the costs of adults
     *
     * @param count number of adults
     *
     * @return entry charge for adults
     */
    protected int calculateAdultCost(int count) {
        return count * ADULT_COST;
    }

    /**
     * Calculates the costs of seniors
     *
     * @param count number of seniors
     *
     * @return entry charge for seniors
     */
    protected int calculateSeniorCost(int count) {
        return count * SENIOR_COST;
    }

    /**
     * Computes the number of accompanied children
     *
     * @param childern number of children
     * @param adults   number of adults
     * @param seniors  number of seniors
     *
     * @return number of accompanied children
     */
    protected int getNumberOfAccompaniedChildren(int children, int adults, int seniors) {
        if (children == 0) {
            // there are no children
            return 0;
        }

        int guardians = adults + seniors;
        int accompaniedChildren = children > guardians ? guardians : children;

        return accompaniedChildren;
    }

    /**
     * Computes the number of unaccompanied children
     *
     * @param childern number of children
     * @param adults   number of adults
     * @param seniors  number of seniors
     *
     * @return number of unaccompanied children
     */
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
