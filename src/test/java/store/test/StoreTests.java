package store.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import store.model.*;
import store.service.*;
import store.exception.*;
import store.util.FileUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class StoreTests {

    private Inventory inventory;
    private Cashier cashier;
    private CashDesk cashDesk;

    @BeforeEach
    public void setup() {
        inventory = new Inventory();
        cashier = new Cashier("C1", "Alex", 1500);
        cashDesk = new CashDesk(cashier, inventory);

        inventory.addProduct(new FoodProduct("F1", "Milk", 1.0, LocalDate.now().plusDays(5), 10));
        inventory.addProduct(new FoodProduct("F2", "ExpiredProduct", 1.0, LocalDate.now().minusDays(1), 10));
    }

    @Test
    public void testSuccessfulSaleReducesQuantity() throws Exception {
        cashDesk.sell(Map.of("F1", 2));
        Product p = inventory.getProduct("F1");
        assertEquals(8, p.getQuantity(), "Quantity should be reduced after sale");
    }

    @Test
    public void testInsufficientStockException() {
        assertThrows(InsufficientStockException.class, () -> {
            cashDesk.sell(Map.of("F1", 20));
        }, "Should throw InsufficientStockException when quantity is insufficient");
    }

    @Test
    public void testExpiredProductException() {
        assertThrows(ExpiredProductException.class, () -> {
            cashDesk.sell(Map.of("F2", 1));
        }, "Should throw ExpiredProductException when product is expired");
    }

    @Test
    public void testFileUtilSaveAndLoadReceipt() throws Exception {
        Receipt receipt = new Receipt(cashier);
        receipt.addItem(new SaleItem(inventory.getProduct("F1"), 1));
        receipt.finalizeReceipt();

        FileUtil.saveReceipt(receipt);
        Receipt loaded = FileUtil.loadReceipt(receipt.getNumber());

        assertEquals(receipt.getNumber(), loaded.getNumber(), "Receipt number should match");
        assertEquals(receipt.getTotalAmount(), loaded.getTotalAmount(), 1e-6, "Total amount should match");
    }
}
