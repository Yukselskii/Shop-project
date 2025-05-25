package store.service;

import store.model.*;
import store.util.FileUtil;


import java.util.Map;

public class CashDesk {
    private final Cashier cashier;
    private final Inventory inventory;

    public CashDesk(Cashier cashier, Inventory inventory) {
        this.cashier = cashier;
        this.inventory = inventory;
    }

    public Receipt sell(Map<String, Integer> cart) throws Exception {
        Receipt receipt = new Receipt(cashier);
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String id = entry.getKey();
            int qty = entry.getValue();

            inventory.checkAndReduce(id, qty);
            Product product = inventory.getProduct(id);
            SaleItem saleItem = new SaleItem(product, qty);
            receipt.addItem(saleItem);
        }
        receipt.finalizeReceipt();
        FileUtil.saveReceipt(receipt);
        return receipt;
    }
}
