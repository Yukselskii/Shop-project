package store.service;

import store.model.Product;
import store.exception.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

public class Inventory {
    private final Map<String, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public Product getProduct(String id) {
        return products.get(id);
    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }

    public void checkAndReduce(String id, int qty) throws InsufficientStockException, ExpiredProductException {
        Product product = products.get(id);
        if (product == null) {
            throw new InsufficientStockException(id, qty, 0);
        }
        if (product.isExpired()) {
            throw new ExpiredProductException(id);
        }
        if (product.getQuantity() < qty) {
            throw new InsufficientStockException(id, qty, product.getQuantity());
        }
        product.setQuantity(product.getQuantity() - qty);
    }
}
