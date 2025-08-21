package com.jwtvalidator.exception;

public class ClaimsCountValidatorException extends ValidationException {

    public ClaimsCountValidatorException(String message) {
        super(message, "claims_count_validation_failed");
    }
}
