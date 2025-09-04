package com.jwtvalidator.unit.domain.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.jwtvalidator.core.logs.LoggerManager;
import com.jwtvalidator.domain.exception.InvalidSeedException;
import com.jwtvalidator.domain.exception.SeedNotPrimeException;
import com.jwtvalidator.domain.model.Claims;
import com.jwtvalidator.domain.validator.SeedValidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

@ExtendWith(MockitoExtension.class)
public class SeedValidatorTests {

    private SeedValidator seedValidator;
    private LoggerManager<SeedValidator> mockLogger;

    @SuppressWarnings("unchecked")
    @BeforeEach
    public void setUp() throws Exception {
        seedValidator = new SeedValidator();

        mockLogger = mock(LoggerManager.class);

        Field loggerField = SeedValidator.class.getDeclaredField("logger");
        loggerField.setAccessible(true);
        loggerField.set(seedValidator, mockLogger);
    }

    @Test
    public void testValidateWithValidPrimeSeed() {
        Claims claims = new Claims();
        claims.setSeed("7");

        assertDoesNotThrow(() -> seedValidator.validate(claims));

        verify(mockLogger).info("Validating Seed claim");
        verify(mockLogger).info("Seed claim validation passed");
    }

    @Test
    public void testValidateWithNullClaims() {
        assertThrows(InvalidSeedException.class, () -> seedValidator.validate(null));

        verify(mockLogger).info("Validating Seed claim");
        verify(mockLogger).error("Claim or Seed is null");
    }

    @Test
    public void testValidateWithNullSeed() {
        Claims claims = new Claims();
        claims.setSeed(null);

        assertThrows(InvalidSeedException.class, () -> seedValidator.validate(claims));

        verify(mockLogger).info("Validating Seed claim");
        verify(mockLogger).error("Claim or Seed is null");
    }

    @Test
    public void testValidateWithNonNumericSeed() {
        Claims claims = new Claims();
        claims.setSeed("abc");

        InvalidSeedException ex = assertThrows(InvalidSeedException.class, () -> seedValidator.validate(claims));
        assertEquals("Não é um número válido: abc", ex.getMessage());

        verify(mockLogger).info("Validating Seed claim");
        verify(mockLogger).error("Seed is not a valid number: {}", "abc");
    }

    @Test
    public void testValidateWithNonPrimeSeed() {
        Claims claims = new Claims();
        claims.setSeed("8");

        SeedNotPrimeException ex = assertThrows(SeedNotPrimeException.class, () -> seedValidator.validate(claims));
        assertEquals("Seed não é um número primo: 8", ex.getMessage());

        verify(mockLogger).info("Validating Seed claim");
        verify(mockLogger).error("Seed is not a prime number: {}", 8L);
    }

    @Test
    public void testGetValidatorName() {
        assertEquals("SeedValidator", seedValidator.getValidatorName());
    }
}
