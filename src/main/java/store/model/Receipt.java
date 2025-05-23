package store.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a receipt with cashier and sold items.
 */
public class Receipt implements Serializable {
    private static long counter = 0;
    private static double totalRevenue = 0;

    private final long number;
    private final Cashier cashier;
    private final LocalDateTime timestamp;
    private final List<SaleItem> items;
    private double totalAmount;

    public Receipt(Cashier cashier) {
        this.cashier = cashier;
        this.timestamp = LocalDateTime.now();
        this.items = new ArrayList<>();
        this.number = ++counter;
        this.totalAmount = 0;
    }

    public void addItem(SaleItem item) {
        items.add(item);
        totalAmount += item.getTotalPrice();
    }

    public void finalizeReceipt() {
        totalRevenue += totalAmount;
    }

    public long getNumber() {
        return number;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public List<SaleItem> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public static long getIssuedReceiptsCount() {
        return counter;
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Форматирана дата
        String formattedDate = timestamp.format(java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));

        sb.append("Receipt #").append(number).append("\n");
        sb.append("Cashier: ").append(cashier.getName()).append("\n");
        sb.append("Date: ").append(formattedDate).append("\n");
        sb.append("Items:\n");

        for (SaleItem item : items) {
            sb.append(String.format(" - %s x%d | %.2f BGN = %.2f BGN\n",
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getUnitPrice(),
                    item.getTotalPrice()));
        }

        sb.append(String.format("Total: %.2f BGN\n", totalAmount));

        return sb.toString();
    }

}
