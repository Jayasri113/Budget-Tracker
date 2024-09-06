import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PersonalBudgetTracker {

    private static final Scanner scanner = new Scanner(System.in);
    private static Map<String, Double> categories = new HashMap<>();
    private static List<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        setupCategories();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Personal Budget Tracker ---");
            System.out.println("1. Add Transaction");
            System.out.println("2. View Transactions");
            System.out.println("3. View Spending by Category");
            System.out.println("4. Calculate Total Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.println();

            switch (choice) {
                case 1:
                    addTransaction();
                    break;
                case 2:
                    viewTransactions();
                    break;
                case 3:
                    viewSpendingByCategory();
                    break;
                case 4:
                    calculateTotalBalance();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }

        scanner.close();
    }

    private static void setupCategories() {
        categories.put("Groceries", 0.0);
        categories.put("Entertainment", 0.0);
        categories.put("Utilities", 0.0);
        categories.put("Rent", 0.0);
        // Add more categories as needed
    }

    private static void addTransaction() {
        System.out.println("Enter transaction details:");
        System.out.print("Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Category (Groceries, Entertainment, Utilities, Rent, etc.): ");
        String category = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();

        Transaction transaction = new Transaction(amount, category, description);
        transactions.add(transaction);

        // Update category total
        categories.put(category, categories.get(category) + amount);

        System.out.println("Transaction added successfully!");
    }

    private static void viewTransactions() {
        System.out.println("All Transactions:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    private static void viewSpendingByCategory() {
        System.out.println("Spending by Category:");
        for (Map.Entry<String, Double> entry : categories.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
    }

    private static void calculateTotalBalance() {
        double total = 0;
        for (double amount : categories.values()) {
            total += amount;
        }
        System.out.println("Total Balance: $" + total);
    }

    static class Transaction {
        private double amount;
        private String category;
        private String description;

        public Transaction(double amount, String category, String description) {
            this.amount = amount;
            this.category = category;
            this.description = description;
        }

        @Override
        public String toString() {
            return "Amount: $" + amount + ", Category: " + category + ", Description: " + description;
        }
    }
}
