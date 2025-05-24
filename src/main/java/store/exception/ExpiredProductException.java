package store.exception;

public class ExpiredProductException extends Exception {
    public ExpiredProductException(String id) {
        super(String.format("Product %s is expired and cannot be sold.", id));
    }
}
