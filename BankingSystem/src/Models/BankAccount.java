package Models;




import java.util.ArrayList;
import java.util.List;

public abstract class BankAccount {

    protected String accountNumber;
    protected String accountHolder;
    protected double balance;

    protected final List<Transaction> transactions =
            new ArrayList<>();

    protected BankAccount(String accountNumber,
                          String accountHolder,
                          double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public abstract boolean withdraw(double amount);

    public abstract double getMinimumBalance();

    public boolean deposit(double amount) {

        if (!Double.isFinite(amount)
                || amount <= 0
                || amount > 100000) {
            System.out.println(
                    "Invalid deposit amount. Enter an amount "
                    + "greater than ₹0 and at most ₹100000.");
            return false;
        }

        balance += amount;

        transactions.add(new Transaction(
                "DEPOSIT", amount, "Money deposited"));

        System.out.println("Deposit successful.");
        return true;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public void getStatement() {

        System.out.println("\n===== ACCOUNT STATEMENT =====");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.printf("Current Balance: ₹%.2f%n", balance);
        System.out.println("--------------------------------");

        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }

        System.out.println("==============================");
    }
}
