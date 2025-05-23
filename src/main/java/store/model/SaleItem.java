package store.model;

public class SaleItem {
    private final Product product;
    private final int quantity;
    private final double unitPrice;

    public SaleItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = product.getSellingPrice();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getTotalPrice() {
        return unitPrice * quantity;
    }

    @Override
    public String toString() {
        return String.format("%s x%d @%.2f", product.getName(), quantity, unitPrice);
    }
}

