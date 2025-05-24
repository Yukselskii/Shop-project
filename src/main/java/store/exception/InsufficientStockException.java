package store.exception;

public class InsufficientStockException extends Exception {
    public InsufficientStockException(String id, int requested, int available) {
        super(String.format("Insufficient stock for product %s: requested=%d, available=%d", id, requested, available));
    }
}
