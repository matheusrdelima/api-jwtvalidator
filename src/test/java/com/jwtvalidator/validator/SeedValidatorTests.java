package com.jwtvalidator.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jwtvalidator.exception.InvalidSeedException;
import com.jwtvalidator.exception.SeedNotPrimeException;
import com.jwtvalidator.model.Claims;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SeedValidatorTests {

    private SeedValidator seedValidator;

    @BeforeEach
    public void setUp() {
        seedValidator = new SeedValidator();
    }

    @Test
    public void testValidateWithValidPrimeSeed() {
        Claims claims = new Claims();
        claims.setSeed("7");
        assertDoesNotThrow(() -> seedValidator.validate(claims));
    }

    @Test
    public void testValidateWithNullClaims() {
        assertThrows(InvalidSeedException.class, () -> seedValidator.validate(null));
    }

    @Test
    public void testValidateWithNullSeed() {
        Claims claims = new Claims();
        claims.setSeed(null);
        assertThrows(InvalidSeedException.class, () -> seedValidator.validate(claims));
    }

    @Test
    public void testValidateWithNonNumericSeed() {
        Claims claims = new Claims();
        claims.setSeed("abc");
        InvalidSeedException ex = assertThrows(InvalidSeedException.class, () -> seedValidator.validate(claims));
        assertEquals("Não é um número válido: abc", ex.getMessage());
    }

    @Test
    public void testValidateWithNonPrimeSeed() {
        Claims claims = new Claims();
        claims.setSeed("8");
        SeedNotPrimeException ex = assertThrows(SeedNotPrimeException.class, () -> seedValidator.validate(claims));
        assertEquals("Seed não é um número primo: 8", ex.getMessage());
    }

    @Test
    public void testGetValidatorName() {
        assertEquals("SeedValidator", seedValidator.getValidatorName());
    }
}