package com.jwtvalidator.domain.exception;

public class InvalidNameException extends ValidationException {

    public InvalidNameException(String message) {
        super(message, "name_validation_failed");
    }
}
