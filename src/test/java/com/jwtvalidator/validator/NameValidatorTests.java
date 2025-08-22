package com.jwtvalidator.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jwtvalidator.exception.InvalidNameException;
import com.jwtvalidator.model.Claims;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NameValidatorTests {

    private NameValidator nameValidator;

    @BeforeEach
    public void setUp() {
        nameValidator = new NameValidator();
    }

    @Test
    public void testValidateWithValidName() {
        Claims claims = new Claims();
        claims.setName("NomeValido");
        assertDoesNotThrow(() -> nameValidator.validate(claims));
    }

    @Test
    public void testValidateWithNullClaims() {
        assertThrows(InvalidNameException.class, () -> nameValidator.validate(null));
    }

    @Test
    public void testValidateWithNullName() {
        Claims claims = new Claims();
        claims.setName(null);
        assertThrows(InvalidNameException.class, () -> nameValidator.validate(claims));
    }

    @Test
    public void testValidateWithNameTooLong() {
        Claims claims = new Claims();
        String longName = "a".repeat(257);
        claims.setName(longName);
        InvalidNameException ex = assertThrows(InvalidNameException.class, () -> nameValidator.validate(claims));
        assertEquals("Name maior que o tamanho permitido de caracteres, quantidade atual: 257 quantidade maxima: 256", ex.getMessage());
    }

    @Test
    public void testValidateWithNameContainingNumbers() {
        Claims claims = new Claims();
        claims.setName("Nome123");
        InvalidNameException ex = assertThrows(InvalidNameException.class, () -> nameValidator.validate(claims));
        assertEquals("Name contém números: Nome123", ex.getMessage());
    }

    @Test
    public void testGetValidatorName() {
        assertEquals("NameValidator", nameValidator.getValidatorName());
    }

    @Test
    public void testValidateWithNameAtMaxLength() {
        Claims claims = new Claims();
        String maxLengthName = "a".repeat(256);
        claims.setName(maxLengthName);

        assertDoesNotThrow(() -> nameValidator.validate(claims));
    }    
}