package com.jwtvalidator.domain.exception;

public class InvalidRoleException extends ValidationException {

    public InvalidRoleException(String message) {
        super(message, "role_validation_failed");
    }
}
