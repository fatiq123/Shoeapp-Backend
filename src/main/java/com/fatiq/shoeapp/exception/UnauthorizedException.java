package com.fatiq.shoeapp.exception;

// For authentication/authorization failures (e.g., wrong credentials).
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
