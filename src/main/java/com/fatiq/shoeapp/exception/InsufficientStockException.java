package com.fatiq.shoeapp.exception;

// For when a shoe’s stock is insufficient for an order.
public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message) {
        super(message);
    }
}
