package com.jwtvalidator.exception;

public class InvalidRoleException extends ValidationException {

    public InvalidRoleException(String message) {
        super(message, "role_validation_failed");
    }
}
