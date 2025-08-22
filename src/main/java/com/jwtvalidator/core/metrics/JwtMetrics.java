package com.jwtvalidator.core.metrics;

public interface JwtMetrics {
    void incrementValidationSuccessCount();

    void incrementValidationFailureCount();
}
