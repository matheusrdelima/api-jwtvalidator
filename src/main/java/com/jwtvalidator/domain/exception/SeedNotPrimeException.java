package com.jwtvalidator.domain.exception;

public class SeedNotPrimeException extends ValidationException {

    public SeedNotPrimeException(String message) {
        super(message, "seed_not_prime");
    }
}
