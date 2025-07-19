// This class will store all the transactions in an
// array list. It will also group the totals by category
// with a HashMap. It will have an undoing the last
// transaction option with Stack.
// I implemented 3 algorithms: search by category, selection sort,
// and summation with loops.


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class BudgetTracker {
    // creates a list to store all transactions
    private ArrayList<Transaction> transactions;

    // stack to support undo feature,
    // lets us remove the last thing added
    private Stack<Transaction> undoStack;

    // Map to group expenses by category and track totals
    // ex.. Food = $50)
    private HashMap<String, Double> categoryTotals;

    // Monthly budget limit
    private double budgetLimit;

    private double monthlyBudget = 0;

    // Method initializing data structures, runs when we create a new
    // BudgetTracker object
    public BudgetTracker() {
        // Initializes the empty list
        transactions = new ArrayList<>();
        // Initializes the undo stack
        undoStack = new Stack<>();
        // Initializes the category total map
        categoryTotals = new HashMap<>();
        // Starts with no budget set
        budgetLimit = 0.0;
    }

    // Adds a new transaction to the system
    public void addTransaction(Transaction t) {
        // Add to the list of all transactions
        transactions.add(t);
        // Save it in case the user wants to undo           
        undoStack.push(t);             
        // Update the category total in the HashMap
        // Get the category name
        String category = t.getCategory();
        // Get current total for this category                  
        double currentTotal = categoryTotals.getOrDefault(category, 0.0);
        // Add this transaction’s amount to the category total
        categoryTotals.put(category, currentTotal + t.getAmount());      
    }

    // undo feature to remove the most recent transaction
    public void undoLastTransaction() {
        // if the undoStack has elements in it
        if (!undoStack.isEmpty()) {
            // gets the last added transaction
            Transaction last = undoStack.pop();
            // remove it from main list
            transactions.remove(last);

            // Subtract its amount from the category total
            // Get the category of the last transaction (ex "Food", "Rent")
            String category = last.getCategory();
            // Get the current total for that category from the HashMap
            // If the category doesn't exist in the map yet, use 0.0 as the default value
            double currentTotal = categoryTotals.getOrDefault(category, 0.0);
            // Subtract the amount of the last transaction from the total for this category
            // Then update the new value back into the HashMap
            categoryTotals.put(category, currentTotal - last.getAmount());
            // Ex: (Food: 100), if user undoes a transaction worth $25 from
            // the food category, it will get "Food" from the transaction
            // look up the current total for that category which is 100
            // then it will subtract 25 and create a new total to return
            System.out.println("Last transaction removed successfully.");
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    // Adds up all money spent
    public double getTotalSpent() {
        double total = 0;
        // Loop through all transactions
        for (Transaction t : transactions) {
            // add each transaction's amount to the total
            total += t.getAmount();
        }
        return total;
    }

    // user sets a budget limit
    public void setBudgetLimit(double limit) {
        budgetLimit = limit;
    }

    public void setMonthlyBudget(double budget) {
        this.monthlyBudget = budget;
        System.out.println("Monthly budget set to $" + budget);
    }

    // Returns true if the user has gone over their budget
    public boolean isOverBudget() {
        // compare current spending to limit
        return getTotalSpent() > budgetLimit;
    }

    // Prints total spent in each category (Food, Rent, etc.)
    public void showCategoryTotals() {
        System.out.println("Category Totals:");
        // loop through each category name stored in HasMap
        for (String category : categoryTotals.keySet()) {
            // get the total amount in this category
            double total = categoryTotals.get(category);
            System.out.println(category + ": $" + total);
        }
    }

    // Searches for and displays all transactions that match a specific category
    public void searchByCategory(String searchCategory) {
        System.out.println("Transactions in category: " + searchCategory);

        // Loop through all the transactions we’ve recorded
        for (Transaction t : transactions) {
            // Check if this transaction's category matches the one the user typed in
            // equalsIgnoreCase means it ignores uppercase vs lowercase differences
            if (t.getCategory().equalsIgnoreCase(searchCategory)) {
                // Print this matching transaction using its toString() format
                System.out.println(t);
            }
        }
    }

    // Sorts the transaction list by amount from smallest to largest
    public void sortByAmount() {
        // Outer loop goes through each index in the list
        for (int i = 0; i < transactions.size() - 1; i++) {
            int minIndex = i; // Assume the current item is the smallest

            // Inner loop searches for the smallest value in the remaining list
            for (int j = i + 1; j < transactions.size(); j++) {
                // If we find a transaction with a smaller amount than the current min,
                // we update minIndex to that position
                if (transactions.get(j).getAmount() < transactions.get(minIndex).getAmount()) {
                    minIndex = j;
                }
            }
            // After finding the smallest transaction in the remaining list,
            // we swap it with the one at the current index (i)
            // Save current transaction
            Transaction temp = transactions.get(i);
            // Move smaller transaction into position i                     
            transactions.set(i, transactions.get(minIndex));
            // Put the original i transaction in minIndex            
            transactions.set(minIndex, temp);                            
        }
        System.out.println("Transactions sorted by amount (lowest to highest).");

    }

    // print every transaction that's been used so far
    public void printAllTransactions() {
        // if the list is empty, let user know
        if (transactions.isEmpty()) {
            System.out.println("No transactions recorded.");
        } else {
            // if not empty loop through transactions to print
            for (Transaction t: transactions) {
                // uses toString() method to format output
                System.out.println(t);
            }
        }
    }

}