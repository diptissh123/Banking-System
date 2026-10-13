package Models;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private final LocalDateTime date;
    private final String type;
    private final double amount;
    private final String details;

    public Transaction(String type, double amount, String details) {
        this.date = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
        this.details = details;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return date.format(formatter)
                + " | " + type
                + " | Amount: ₹" + String.format("%.2f", amount)
                + " | " + details;
    }
}
