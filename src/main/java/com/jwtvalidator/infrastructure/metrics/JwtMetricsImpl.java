package com.jwtvalidator.infrastructure.metrics;

import org.springframework.stereotype.Component;

import com.jwtvalidator.core.logs.LoggerManager;
import com.jwtvalidator.core.metrics.JwtMetrics;
import com.jwtvalidator.infrastructure.logs.LoggerManagerImpl;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class JwtMetricsImpl implements JwtMetrics {

    private final Counter validationSuccessCounter;
    private final Counter validationFailureCounter;

    private final LoggerManager<JwtMetricsImpl> logger = new LoggerManagerImpl<>(JwtMetricsImpl.class);

    public JwtMetricsImpl(MeterRegistry meterRegistry) {
        this.validationSuccessCounter = meterRegistry.counter("jwt_validation_success_total");
        this.validationFailureCounter = meterRegistry.counter("jwt_validation_failure_total");
    }

    @Override
    public void incrementValidationSuccessCount() {
        logger.info("Incrementing validation success counter");
        validationSuccessCounter.increment();
    }

    @Override
    public void incrementValidationFailureCount() {
        logger.info("Incrementing validation failure counter");
        validationFailureCounter.increment();
    }
}
