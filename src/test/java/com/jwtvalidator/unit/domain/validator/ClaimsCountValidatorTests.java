package com.jwtvalidator.unit.domain.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jwtvalidator.domain.exception.ClaimsCountValidatorException;
import com.jwtvalidator.domain.exception.InvalidClaimsException;
import com.jwtvalidator.domain.model.Claims;
import com.jwtvalidator.domain.validator.ClaimsCountValidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class ClaimsCountValidatorTests {

    private ClaimsCountValidator claimsCountValidator;

    @BeforeEach
    public void setUp() {
        claimsCountValidator = new ClaimsCountValidator();
    }

    @Test
    public void testValidateWithValidClaimsCount() {
        Claims claims = new Claims();
        Map<String, String> allClaims = new HashMap<>();
        allClaims.put("name", "Nome");
        allClaims.put("role", "Admin");
        allClaims.put("seed", "2");
        claims.setAllClaims(allClaims);
        assertDoesNotThrow(() -> claimsCountValidator.validate(claims));
    }

    @Test
    public void testValidateWithNullClaims() {
        assertThrows(InvalidClaimsException.class, () -> claimsCountValidator.validate(null));
    }

    @Test
    public void testValidateWithNullAllClaims() {
        Claims claims = new Claims();
        claims.setAllClaims(null);
        assertThrows(InvalidClaimsException.class, () -> claimsCountValidator.validate(claims));
    }

    @Test
    public void testValidateWithEmptyAllClaims() {
        Claims claims = new Claims();
        claims.setAllClaims(new HashMap<>());
        assertThrows(InvalidClaimsException.class, () -> claimsCountValidator.validate(claims));
    }

    @Test
    public void testValidateWithMoreThanExpectedClaims() {
        Claims claims = new Claims();
        Map<String, String> allClaims = new HashMap<>();
        allClaims.put("name", "Nome");
        allClaims.put("role", "Admin");
        allClaims.put("seed", "2");
        allClaims.put("extra", "valor");
        claims.setAllClaims(allClaims);
        ClaimsCountValidatorException ex = assertThrows(ClaimsCountValidatorException.class, () -> claimsCountValidator.validate(claims));
        assertEquals("Claims inválidas: apenas Name, Seed e Role são válidos. Enviado: " + allClaims, ex.getMessage());
    }

    @Test
    public void testValidateWithLessThanExpectedClaims() {
        Claims claims = new Claims();
        Map<String, String> allClaims = new HashMap<>();
        allClaims.put("name", "Nome");
        allClaims.put("role", "Admin");
        claims.setAllClaims(allClaims);
        ClaimsCountValidatorException ex = assertThrows(ClaimsCountValidatorException.class, () -> claimsCountValidator.validate(claims));
        assertEquals("Claims inválidas: apenas Name, Seed e Role são válidos. Enviado: " + allClaims, ex.getMessage());
    }

    @Test
    public void testGetValidatorName() {
        assertEquals("ClaimsValidator", claimsCountValidator.getValidatorName());
    }
}