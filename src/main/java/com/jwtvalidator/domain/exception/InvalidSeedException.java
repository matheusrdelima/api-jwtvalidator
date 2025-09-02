package com.jwtvalidator.domain.exception;

public class InvalidSeedException extends ValidationException {

    public InvalidSeedException(String message) {
        super(message, "seed_validation_failed");
    }
}
