package com.jwtvalidator.unit.domain.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.jwtvalidator.core.logs.LoggerManager;
import com.jwtvalidator.domain.exception.InvalidNameException;
import com.jwtvalidator.domain.model.Claims;
import com.jwtvalidator.domain.validator.NameValidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

@ExtendWith(MockitoExtension.class)
public class NameValidatorTests {

    private NameValidator nameValidator;
    private LoggerManager<NameValidator> mockLogger;

    @SuppressWarnings("unchecked")
    @BeforeEach
    public void setUp() throws Exception {
        nameValidator = new NameValidator();

        mockLogger = mock(LoggerManager.class);

        Field loggerField = NameValidator.class.getDeclaredField("logger");
        loggerField.setAccessible(true);
        loggerField.set(nameValidator, mockLogger);
    }

    @Test
    public void testValidateWithValidName() {
        Claims claims = new Claims();
        claims.setName("NomeValido");

        assertDoesNotThrow(() -> nameValidator.validate(claims));

        verify(mockLogger).info("Validating Name claim");
        verify(mockLogger).info("Name claim validation passed");
    }

    @Test
    public void testValidateWithNullClaims() {
        assertThrows(InvalidNameException.class, () -> nameValidator.validate(null));

        verify(mockLogger).info("Validating Name claim");
        verify(mockLogger).error("Claim or Name is null");
    }

    @Test
    public void testValidateWithNullName() {
        Claims claims = new Claims();
        claims.setName(null);

        assertThrows(InvalidNameException.class, () -> nameValidator.validate(claims));

        verify(mockLogger).info("Validating Name claim");
        verify(mockLogger).error("Claim or Name is null");
    }

    @Test
    public void testValidateWithNameTooLong() {
        Claims claims = new Claims();
        String longName = "a".repeat(257);
        claims.setName(longName);

        InvalidNameException ex = assertThrows(InvalidNameException.class, () -> nameValidator.validate(claims));
        assertEquals(
            "Name maior que o tamanho permitido de caracteres, quantidade atual: 257 quantidade maxima: 256",
            ex.getMessage()
        );

        verify(mockLogger).info("Validating Name claim");
        verify(mockLogger).error("Name exceeds maximum length: {}", 257);
    }

    @Test
    public void testValidateWithNameContainingNumbers() {
        Claims claims = new Claims();
        claims.setName("Nome123");

        InvalidNameException ex = assertThrows(InvalidNameException.class, () -> nameValidator.validate(claims));
        assertEquals("Name contém números: Nome123", ex.getMessage());

        verify(mockLogger).info("Validating Name claim");
        verify(mockLogger).error("Name contains numbers: {}", "Nome123");
    }

    @Test
    public void testValidateWithNameAtMaxLength() {
        Claims claims = new Claims();
        String maxLengthName = "a".repeat(256);
        claims.setName(maxLengthName);

        assertDoesNotThrow(() -> nameValidator.validate(claims));

        verify(mockLogger).info("Validating Name claim");
        verify(mockLogger).info("Name claim validation passed");
    }

    @Test
    public void testGetValidatorName() {
        assertEquals("NameValidator", nameValidator.getValidatorName());
    }
}
