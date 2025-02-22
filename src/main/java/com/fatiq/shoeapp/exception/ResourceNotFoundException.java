package com.fatiq.shoeapp.exception;

// For when a requested resource (e.g., shoe, brand, user) isn’t found.
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
