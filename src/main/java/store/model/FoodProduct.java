package store.model;

import java.time.LocalDate;

/**
 * Клас за хранителни стоки с конкретни стойности за надценка и отстъпка.
 */
public class FoodProduct extends Product {
    private static double markupPercent = 20.0;
    private static long discountThresholdDays = 3;
    private static double discountPercent = 10.0;

    public FoodProduct(String id, String name, double deliveryPrice, LocalDate expirationDate, int quantity) {
        super(id, name, deliveryPrice, ProductType.FOOD, expirationDate, quantity);
    }

    @Override
    protected double getMarkupPercent() {
        return markupPercent;
    }

    @Override
    protected long getDiscountThresholdDays() {
        return discountThresholdDays;
    }

    @Override
    protected double getDiscountPercent() {
        return discountPercent;
    }

    // Опционални сетъри за магазините, ако искат да сменят стойностите
    public static void setMarkupPercent(double percent) {
        markupPercent = percent;
    }

    public static void setDiscountThresholdDays(long days) {
        discountThresholdDays = days;
    }

    public static void setDiscountPercent(double percent) {
        discountPercent = percent;
    }
}

