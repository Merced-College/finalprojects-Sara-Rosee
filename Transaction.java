// This Transaction class represents the expenses
// from the user, in our budget tracker.

public class Transaction {
    // private: only this class can access this variable directly
    // stores amount of money spent
    private double amount;
    // stores the category of the expense (Food, rent, etc.)
    private String category;
    // user can enter description to describe the transaction
    private String description;

    // A method called when user inputs variables for transaction
    public Transaction(double amount, String category, String description) {
        // this.amount -> Set the object's amount (the one declared in the class) to the 
        // value of the amount passed into the constructor"
        this.amount = amount;           // sets the amount field to the value passed in
        this.category = category;       // sets the category field
        this.description = description; // sets the description field
    }

    // lets another class access the amount
    public double getAmount() {
        return amount;
    }
    // gets the category
    public String getCategory() {
        return category;
    }

    // gets the description
    public String getDescription() {
        return description;
    }

    // allows changing the amount if needed
    public void setAmount(double amount) {
        this.amount = amount;
    }

    // allows changing the category
    public void setCategory(String category) {
        this.category = category;
    }

    // allows changing the description
    public void setDescription(String description) {
        this.description = description;
    }

    // override the to_String() method in java to a clean and readable version
    @Override
    public String toString() {
        return "Transaction {\n" +
            "  amount = $" + amount + ",\n" +
            "  category = '" + category + "',\n" +
            "  description = '" + description + "'\n" +
            '}';
    }
    // example display:
    // amount = $25.0,
    // category = 'Food',
    // description = 'Lunch at cafe'

}