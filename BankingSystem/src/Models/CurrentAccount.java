package Models;


public class CurrentAccount extends BankAccount {

    private static final double OVERDRAFT_LIMIT = 5000.00;

    public CurrentAccount(String accountNumber,
                          String accountHolder,
                          double initialBalance) {

        super(accountNumber, accountHolder, initialBalance);

        if (!Double.isFinite(initialBalance)
                || initialBalance < 0) {
            throw new IllegalArgumentException(
                    "Initial balance cannot be negative.");
        }

        transactions.add(new Transaction(
                "ACCOUNT_OPENED", initialBalance,
                "Current account opened"));
    }

    @Override
    public boolean withdraw(double amount) {

        if (!Double.isFinite(amount)
                || amount <= 0 || amount > 100000) {
            System.out.println("Invalid withdrawal amount.");
            return false;
        }

        if (balance - amount < -OVERDRAFT_LIMIT) {
            System.out.println(
                    "Insufficient funds. Overdraft limit "
                    + "of Rs. 5000 exceeded.");
            return false;
        }

        balance -= amount;

        transactions.add(new Transaction(
                "WITHDRAWAL", amount,
                "Current account withdrawal"));

        System.out.println("Withdrawal successful.");
        return true;
    }

    @Override
    public double getMinimumBalance() {
        return -OVERDRAFT_LIMIT;
    }
}

