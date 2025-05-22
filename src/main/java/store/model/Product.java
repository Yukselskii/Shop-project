package store.model;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Абстрактен клас за общите характеристики на всеки продукт.
 */

public abstract class Product implements Serializable {
    private String id;
    private String name;
    private double deliveryPrice;
    private ProductType type;
    private LocalDate expirationDate;
    protected int quantity;

    public Product(String id, String name, double deliveryPrice, ProductType type, LocalDate expirationDate, int quantity) {
        this.id = id;
        this.name = name;
        this.deliveryPrice = deliveryPrice;
        this.type = type;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public ProductType getType() {
        return type;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    /**
     * Изчислява продажната цена според надценка и евентуално отстъпка, ако наближава срока.
     */

    public double getSellingPrice() {
        double price = deliveryPrice * (1 + getMarkupPercent() / 100.0);

        long daysleft = ChronoUnit.DAYS.between(LocalDate.now(), expirationDate);
        if (daysleft >= 0 && daysleft < getDiscountThresholdDays()) {
            price *= (1 - getDiscountPercent() / 100.0);
        }

        return price;
    }

    public boolean isExpired() {
        return expirationDate.isBefore(LocalDate.now());
    }

    protected abstract double getMarkupPercent();

    protected abstract long getDiscountThresholdDays();

    protected abstract double getDiscountPercent();

    @Override
    public String toString() {
        return String.format("%s (%s) - %.2f BGN [%d pcs] | expires on: %s", name, id, getSellingPrice(), quantity, expirationDate);
    }
}
