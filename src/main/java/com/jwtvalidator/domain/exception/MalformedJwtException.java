package com.jwtvalidator.domain.exception;

public class MalformedJwtException extends ValidationException {

    public MalformedJwtException(String message) {
        super(message, "malformed_jwt");
    }
}
