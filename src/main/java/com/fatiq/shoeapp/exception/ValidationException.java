package com.fatiq.shoeapp.exception;

// For custom validation failures beyond Spring’s @Valid.
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
