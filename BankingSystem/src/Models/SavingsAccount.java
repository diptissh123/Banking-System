package Models;

public class SavingsAccount extends BankAccount {

    private static final double MINIMUM_BALANCE = 1000.00;

    public SavingsAccount(String accountNumber,
                          String accountHolder,
                          double initialBalance) {

        super(accountNumber, accountHolder, initialBalance);

        if (!Double.isFinite(initialBalance)
                || initialBalance < MINIMUM_BALANCE) {
            throw new IllegalArgumentException(
                    "Savings account requires a minimum "
                    + "initial balance of Rs. 1000.");
        }

        transactions.add(new Transaction(
                "ACCOUNT_OPENED", initialBalance,
                "Savings account opened"));
    }

    @Override
    public boolean withdraw(double amount) {

        if (!Double.isFinite(amount)
                || amount <= 0 || amount > 100000) {
            System.out.println("Invalid withdrawal amount.");
            return false;
        }

        if (balance - amount < MINIMUM_BALANCE) {
            System.out.println(
                    "Insufficient funds. Maintain a minimum "
                    + "balance of Rs. 1000.");
            return false;
        }

        balance -= amount;

        transactions.add(new Transaction(
                "WITHDRAWAL", amount, "Savings withdrawal"));

        System.out.println("Withdrawal successful.");
        return true;
    }

    @Override
    public double getMinimumBalance() {
        return MINIMUM_BALANCE;
    }
}
