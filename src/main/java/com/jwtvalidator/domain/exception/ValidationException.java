package com.jwtvalidator.domain.exception;

public abstract class ValidationException extends RuntimeException {

    private final String reason;

    public ValidationException(String message, String reason) {
        super(message);
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
