package com.jwtvalidator.unit.infrastructure.metrics;

import static org.mockito.Mockito.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jwtvalidator.core.logs.LoggerManager;
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

    private JwtMetricsImpl metrics;
    private LoggerManager<JwtMetricsImpl> mockLogger;

    @SuppressWarnings("unchecked")
    @BeforeEach
    void setUp() throws Exception {
        when(meterRegistry.counter("jwt_validation_success_total")).thenReturn(successCounter);
        when(meterRegistry.counter("jwt_validation_failure_total")).thenReturn(failureCounter);

        metrics = new JwtMetricsImpl(meterRegistry);

        mockLogger = mock(LoggerManager.class);
        Field loggerField = JwtMetricsImpl.class.getDeclaredField("logger");
        loggerField.setAccessible(true);
        loggerField.set(metrics, mockLogger);
    }

    @Test
    void testIncrementValidationSuccessCount() {
        metrics.incrementValidationSuccessCount();

        verify(successCounter, times(1)).increment();
        verify(failureCounter, never()).increment();
        verify(mockLogger).info("Incrementing validation success counter");
    }

    @Test
    void testIncrementValidationFailureCount() {
        metrics.incrementValidationFailureCount();

        verify(failureCounter, times(1)).increment();
        verify(successCounter, never()).increment();
        verify(mockLogger).info("Incrementing validation failure counter");
    }
}
