package com.jwtvalidator.exception;

public class InvalidSeedException extends ValidationException {

    public InvalidSeedException(String message) {
        super(message, "seed_validation_failed");
    }
}
