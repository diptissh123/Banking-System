package main;


import java.util.Scanner;

import Models.BankAccount;
import Services.Bank;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Bank bank = new Bank();

    public static void main(String[] args) {

        boolean running = true;

        System.out.println("==================================");
        System.out.println("     BANK MANAGEMENT SYSTEM");
        System.out.println("==================================");

        while (running) {

            displayMenu();

            int choice = readInt("Enter your choice: ");

            try {
                switch (choice) {

                    case 1:
                        createAccount();
                        break;

                    case 2:
                        depositMoney();
                        break;

                    case 3:
                        withdrawMoney();
                        break;

                    case 4:
                        transferMoney();
                        break;

                    case 5:
                        checkBalance();
                        break;

                    case 6:
                        viewStatement();
                        break;

                    case 7:
                        bank.displayAllAccounts();
                        break;

                    case 8:
                        running = false;
                        System.out.println(
                                "Thank you for using our banking system!");
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Please select 1 to 8.");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void displayMenu() {

        System.out.println("----------- MAIN MENU -----------");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Transfer Money");
        System.out.println("5. Check Balance");
        System.out.println("6. View Account Statement");
        System.out.println("7. Display All Accounts");
        System.out.println("8. Exit");
        System.out.println("---------------------------------");
    }

    private static void createAccount() {

        System.out.println("\n--- Create Account ---");

        System.out.print("Enter account holder name: ");
        String holder = scanner.nextLine().trim();

        System.out.print("Enter account type (savings/current): ");
        String type = scanner.nextLine().trim();

        double initialBalance =
                readDouble("Enter initial balance: ");

        BankAccount account =
                bank.createAccount(holder, type, initialBalance);

        System.out.printf("Opening balance: Rs. %.2f%n",
                account.getBalance());
    }

    private static void depositMoney() {

        System.out.println("\n--- Deposit Money ---");

        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine().trim();

        double amount = readDouble("Enter deposit amount: ");

        bank.deposit(accountNumber, amount);
    }

    private static void withdrawMoney() {

        System.out.println("\n--- Withdraw Money ---");

        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine().trim();

        double amount = readDouble("Enter withdrawal amount: ");

        bank.withdraw(accountNumber, amount);
    }

    private static void transferMoney() {

        System.out.println("\n--- Transfer Money ---");

        System.out.print("Enter source account number: ");
        String from = scanner.nextLine().trim();

        System.out.print("Enter destination account number: ");
        String to = scanner.nextLine().trim();

        double amount = readDouble("Enter transfer amount: ");

        bank.transfer(from, to, amount);
    }

    private static void checkBalance() {

        System.out.println("\n--- Check Balance ---");

        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine().trim();

        bank.showBalance(accountNumber);
    }

    private static void viewStatement() {

        System.out.println("\n--- Account Statement ---");

        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine().trim();

        bank.showStatement(accountNumber);
    }

    private static int readInt(String prompt) {

        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(
                        "Invalid input. Please enter a whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {

        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println(
                        "Invalid input. Please enter a valid amount.");
            }
        }
    }
}

