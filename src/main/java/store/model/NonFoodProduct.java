package store.model;

import java.time.LocalDate;

/**
 * Клас за нехранителни стоки. Няма намаление при наближаващ срок.
 */
public class NonFoodProduct extends Product {
    private static final double markupPercent = 30.0;

    public NonFoodProduct(String id, String name, double deliveryPrice, LocalDate expirationDate, int quantity) {
        super(id, name, deliveryPrice, ProductType.NON_FOOD, expirationDate, quantity);
    }

    @Override
    protected double getMarkupPercent() {
        return markupPercent;
    }

    @Override
    protected long getDiscountThresholdDays() {
        return 0;
    }

    @Override
    protected double getDiscountPercent() {
        return 0;
    }


}
