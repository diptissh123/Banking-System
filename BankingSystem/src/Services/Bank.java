package Services;


import java.util.HashMap;
import java.util.Map;

import Models.BankAccount;
import Models.SavingsAccount;
import Models.CurrentAccount;
import Models.Transaction;

public class Bank {

    private final Map<String, BankAccount> accounts =
            new HashMap<>();

    private int nextAccountNumber = 1001;

    public BankAccount createAccount(
            String holder, String type, double initialBalance) {

        if (holder == null || holder.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Account holder name cannot be empty.");
        }

        if (!Double.isFinite(initialBalance)
                || initialBalance < 0) {
            throw new IllegalArgumentException(
                    "Initial balance must be valid and non-negative.");
        }

        String accountNumber = "ACC" + nextAccountNumber;

        BankAccount account;

        if (type.equalsIgnoreCase("savings")) {
            account = new SavingsAccount(
                    accountNumber, holder, initialBalance);

        } else if (type.equalsIgnoreCase("current")) {
            account = new CurrentAccount(
                    accountNumber, holder, initialBalance);

        } else {
            throw new IllegalArgumentException(
                    "Invalid account type. Choose savings or current.");
        }

        accounts.put(accountNumber, account);
        nextAccountNumber++;

        System.out.println("Account created successfully!");
        System.out.println("Account Number: " + accountNumber);

        return account;
    }

    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }

        System.out.println("\n===== ALL BANK ACCOUNTS =====");

        for (BankAccount account : accounts.values()) {
            System.out.println(
                    account.getAccountNumber()
                    + " | " + account.getAccountHolder()
                    + " | Balance: Rs. "
                    + String.format("%.2f", account.getBalance()));
        }
    }

    public boolean transfer(String fromNumber,
                            String toNumber,
                            double amount) {

        if (!Double.isFinite(amount)
                || amount <= 0 || amount > 100000) {
            System.out.println("Invalid transfer amount.");
            return false;
        }

        if (fromNumber.equals(toNumber)) {
            System.out.println(
                    "Source and destination accounts must differ.");
            return false;
        }

        BankAccount from = accounts.get(fromNumber);
        BankAccount to = accounts.get(toNumber);

        if (from == null || to == null) {
            System.out.println(
                    "Source or destination account not found.");
            return false;
        }

        // Withdraw first. If it fails, do not deposit.
        if (!from.withdraw(amount)) {
            System.out.println("Transfer cancelled.");
            return false;
        }

        if (!to.deposit(amount)) {
            // Defensive rollback in case destination deposit fails.
            // Under normal operation, a valid amount will be accepted.
            from.deposit(amount);
            System.out.println("Transfer failed; amount refunded.");
            return false;
        }

        System.out.println("Transfer successful!");

        return true;
    }

    public boolean deposit(String accountNumber, double amount) {
        BankAccount account = accounts.get(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return false;
        }

        return account.deposit(amount);
    }

    public boolean withdraw(String accountNumber, double amount) {
        BankAccount account = accounts.get(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return false;
        }

        return account.withdraw(amount);
    }

    public void showBalance(String accountNumber) {
        BankAccount account = accounts.get(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.printf("Account: %s%nBalance: Rs. %.2f%n",
                account.getAccountNumber(),
                account.getBalance());
    }

    public void showStatement(String accountNumber) {
        BankAccount account = accounts.get(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        account.getStatement();
    }
}

