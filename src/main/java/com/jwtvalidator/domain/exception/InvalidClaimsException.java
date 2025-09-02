package com.jwtvalidator.domain.exception;

public class InvalidClaimsException extends ValidationException {

    public InvalidClaimsException(String message) {
        super(message, "claims_validation_failed");
    }
}
