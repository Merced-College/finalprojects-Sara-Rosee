// this file will display the menu options
// take user input with scanner, and call the methods
// from the BudgetTracker object

// Main.java

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a Scanner to read user input
        Scanner input = new Scanner(System.in);
        // Create a BudgetTracker object to use           
        BudgetTracker tracker = new BudgetTracker();

        // As long as running is true, keep looping and showing the menu
        // if running = false; stop loop and exit app
        boolean running = true;

        System.out.println("Welcome to the Budget Tracker! 📊");

        // running will continue to run until it becomes false (user enters 0)
        while (running) {
            // Show menu options
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a new expense");
            System.out.println("2. Show total spent");
            System.out.println("3. Show expenses by category");
            System.out.println("4. Undo last expense");
            System.out.println("5. Search by category");
            System.out.println("6. Sort expenses by amount");
            System.out.println("7. Set monthly budget");
            System.out.println("8. Check if over budget");
            System.out.println("9. Show all transactions");
            System.out.println("0. Exit");

            // Get user choice
            System.out.print("Enter your choice: ");
            // reads number typed
            String choiceStr = input.nextLine();
            int choice;

            // if user doesn't input an integer in the menu option
            try {
                choice = Integer.parseInt(choiceStr); // try converting to int
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number from 0 to 9.");
                continue;  // skip the rest of this loop and go back to menu
            }


            // Decide what to do based on the user's choice
            switch (choice) {
                case 1:
                    // Add a new expense
                    System.out.print("Enter amount: ");
                    double amount = input.nextDouble();
                    input.nextLine();

                    System.out.print("Enter category (ex: Food, Rent, Fashion, (any category works!)): ");
                    String category = input.nextLine();

                    System.out.print("Enter description: ");
                    String description = input.nextLine();

                    Transaction t = new Transaction(amount, category, description);
                    tracker.addTransaction(t);
                    System.out.println("Expense added!");
                    break;

                case 2:
                    // Show total spent
                    System.out.println("Total spent: $" + tracker.getTotalSpent());
                    break;

                case 3:
                    // Show totals by category
                    tracker.showCategoryTotals();
                    break;

                case 4:
                    // Undo last expense
                    tracker.undoLastTransaction();
                    break;

                case 5:
                    // Search by category
                    System.out.print("Enter category to search: ");
                    String search = input.nextLine();
                    tracker.searchByCategory(search);
                    break;

                case 6:
                    // Sort expenses by amount
                    tracker.sortByAmount();
                    break;

                case 7:
                    // Set monthly budget
                    System.out.print("Enter monthly budget limit: ");
                    double budgetLimit = input.nextDouble();
                    input.nextLine();
                    tracker.setMonthlyBudget(budgetLimit);  
                    break;

                case 8:
                    // Check if over budget
                    if (tracker.isOverBudget()) {
                        System.out.println("⚠️ You are over budget!");
                    } else {
                        System.out.println("✅ You are within your budget.");
                    }
                    break;

                case 9:
                    // Show all transactions
                    tracker.printAllTransactions();
                    break;

                case 0:
                    // Exit the program
                    running = false;
                    System.out.println("Goodbye! Thanks for using the Budget Tracker. 👋");
                    break;

                default:
                    System.out.println("Invalid option. Please choose a number between 0 and 9.");
            }
        }

        input.close();  // Close the scanner
    }
}
