package store.app;

import store.model.*;
import store.service.*;
import store.exception.*;

import java.time.LocalDate;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Inventory inventory = new Inventory();
    private static CashDesk cashDesk;

    public static void main(String[] args) {
        System.out.println("=== Welcome to Store Management ===");

        // Add a cashier
        Cashier cashier = new Cashier("C1", "Alex", 1500);
        cashDesk = new CashDesk(cashier, inventory);

        // Add some sample products
        inventory.addProduct(new FoodProduct("F1", "Yogurt", 1.2, LocalDate.now().plusDays(5), 10));
        inventory.addProduct(new NonFoodProduct("N1", "Soap", 0.8, LocalDate.now().plusMonths(6), 20));

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add new product");
            System.out.println("2. Show inventory");
            System.out.println("3. Make a sale");
            System.out.println("4. Show revenue and profit");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addProduct();
                    break;
                case "2":
                    showInventory();
                    break;
                case "3":
                    makeSale();
                    break;
                case "4":
                    showFinancialInfo();
                    break;
                case "5":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void addProduct() {
        try {
            System.out.print("Enter product ID: ");
            String id = scanner.nextLine();
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Delivery price: ");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Type (FOOD/NON_FOOD): ");
            ProductType type = ProductType.valueOf(scanner.nextLine().toUpperCase());
            System.out.print("Expiration date (yyyy-MM-dd): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            System.out.print("Quantity: ");
            int qty = Integer.parseInt(scanner.nextLine());

            Product product = (type == ProductType.FOOD)
                    ? new FoodProduct(id, name, price, date, qty)
                    : new NonFoodProduct(id, name, price, date, qty);

            inventory.addProduct(product);
            System.out.println("Product added.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void showInventory() {
        for (Product p : inventory.getAllProducts()) {
            System.out.println(p);
        }
    }

    private static void makeSale() {
        try {
            Map<String, Integer> cart = new HashMap<>();
            while (true) {
                System.out.print("Enter product ID (or 'done'): ");
                String id = scanner.nextLine();
                if (id.equalsIgnoreCase("done")) break;
                System.out.print("Quantity: ");
                int qty = Integer.parseInt(scanner.nextLine());
                cart.put(id, qty);
            }
            Receipt receipt = cashDesk.sell(cart);
            System.out.println("\n=== Receipt ===");
            System.out.println(receipt);
        } catch (InsufficientStockException | ExpiredProductException e) {
            System.out.println("Sale failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    private static void showFinancialInfo() {
        System.out.printf("Total revenue: %.2f BGN\n", Receipt.getTotalRevenue());
        // For simplicity, salary and delivery costs can be hardcoded or extended later
    }
}
