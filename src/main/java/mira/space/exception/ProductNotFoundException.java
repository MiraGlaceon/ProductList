package mira.space.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException() {
        super("Product not found.");
    }
}
