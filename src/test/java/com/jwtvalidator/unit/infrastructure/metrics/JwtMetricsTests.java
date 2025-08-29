package com.jwtvalidator.unit.infrastructure.metrics;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jwtvalidator.infrastructure.metrics.JwtMetricsImpl;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@ExtendWith(MockitoExtension.class)
class JwtMetricsTests {

    @Mock
    private MeterRegistry meterRegistry;

    @Mock
    private Counter successCounter;

    @Mock
    private Counter failureCounter;

    @Test
    void testIncrementValidationSuccessCount() {
        when(meterRegistry.counter("jwt_validation_success_total")).thenReturn(successCounter);
        when(meterRegistry.counter("jwt_validation_failure_total")).thenReturn(failureCounter);

        JwtMetricsImpl metrics = new JwtMetricsImpl(meterRegistry);

        metrics.incrementValidationSuccessCount();

        verify(successCounter, times(1)).increment();
        verify(failureCounter, never()).increment();
    }

    @Test
    void testIncrementValidationFailureCount() {
        when(meterRegistry.counter("jwt_validation_success_total")).thenReturn(successCounter);
        when(meterRegistry.counter("jwt_validation_failure_total")).thenReturn(failureCounter);

        JwtMetricsImpl metrics = new JwtMetricsImpl(meterRegistry);

        metrics.incrementValidationFailureCount();

        verify(failureCounter, times(1)).increment();
        verify(successCounter, never()).increment();
    }
}
