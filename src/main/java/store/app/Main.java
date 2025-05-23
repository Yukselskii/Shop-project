package store.app;

import store.model.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Product yogurt = new FoodProduct("F1", "Yogurt", 1.2, LocalDate.now().plusDays(3), 10);
        Product soap = new NonFoodProduct("N1", "Soap", 0.8, LocalDate.now().plusMonths(6), 20);

        yogurt.setQuantity(5);
        System.out.println("After updating quantity: " + yogurt.getName() + " -> " + yogurt.getQuantity() + " pcs");
        double priceYogurt = yogurt.getSellingPrice();
        double priceSoap = soap.getSellingPrice();

        System.out.printf("Selling price of %s: %.2f\n", yogurt.getName(), priceYogurt);
        System.out.printf("Selling price of %s: %.2f\n", soap.getName(), priceSoap);

        //Create cashier
        Cashier cashier = new Cashier("C1", "Alex", 1500);
        System.out.println("Created cashier:" + cashier);

        //Create SaleItems
        SaleItem item1 = new SaleItem(yogurt, 2);
        SaleItem item2 = new SaleItem(soap, 3);

        //Create Receipt
        Receipt receipt = new Receipt(cashier);
        receipt.addItem(item1);
        receipt.addItem(item2);
        receipt.finalizeReceipt();

        System.out.println("\n=== Simulated Receipt Output ===");
        System.out.println(receipt);
    }
}