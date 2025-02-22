package com.fatiq.shoeapp.exception;

// For duplicate entries (e.g., username, email, brand name).
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
