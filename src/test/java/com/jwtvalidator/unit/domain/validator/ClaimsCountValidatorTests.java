package com.jwtvalidator.unit.domain.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.jwtvalidator.core.logs.LoggerManager;
import com.jwtvalidator.domain.exception.ClaimsCountValidatorException;
import com.jwtvalidator.domain.exception.InvalidClaimsException;
import com.jwtvalidator.domain.model.Claims;
import com.jwtvalidator.domain.validator.ClaimsCountValidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class ClaimsCountValidatorTests {

    private ClaimsCountValidator claimsCountValidator;
    private LoggerManager<ClaimsCountValidator> mockLogger;

    @SuppressWarnings("unchecked")
    @BeforeEach
    public void setUp() throws Exception {
        claimsCountValidator = new ClaimsCountValidator();

        mockLogger = mock(LoggerManager.class);

        Field loggerField = ClaimsCountValidator.class.getDeclaredField("logger");
        loggerField.setAccessible(true);
        loggerField.set(claimsCountValidator, mockLogger);
    }

    @Test
    public void testValidateWithValidClaimsCount() {
        Claims claims = new Claims();
        Map<String, String> allClaims = new HashMap<>();
        allClaims.put("Name", "Nome");
        allClaims.put("Role", "Admin");
        allClaims.put("Seed", "2");
        claims.setAllClaims(allClaims);

        assertDoesNotThrow(() -> claimsCountValidator.validate(claims));

        verify(mockLogger).info("Validating claims count");
        verify(mockLogger).info("Claims count validation passed");
    }

    @Test
    public void testValidateWithNullClaims() {
        assertThrows(InvalidClaimsException.class, () -> claimsCountValidator.validate(null));
        verify(mockLogger).info("Validating claims count");
        verify(mockLogger).error("Claims or allClaims is null or empty");
    }

    @Test
    public void testValidateWithNullAllClaims() {
        Claims claims = new Claims();
        claims.setAllClaims(null);

        assertThrows(InvalidClaimsException.class, () -> claimsCountValidator.validate(claims));
        verify(mockLogger).info("Validating claims count");
        verify(mockLogger).error("Claims or allClaims is null or empty");
    }

    @Test
    public void testValidateWithEmptyAllClaims() {
        Claims claims = new Claims();
        claims.setAllClaims(new HashMap<>());

        assertThrows(InvalidClaimsException.class, () -> claimsCountValidator.validate(claims));
        verify(mockLogger).info("Validating claims count");
        verify(mockLogger).error("Claims or allClaims is null or empty");
    }

    @Test
    public void testValidateWithMoreThanExpectedClaims() {
        Claims claims = new Claims();
        Map<String, String> allClaims = new HashMap<>();
        allClaims.put("Name", "Nome");
        allClaims.put("Role", "Admin");
        allClaims.put("Seed", "2");
        allClaims.put("Extra", "valor");
        claims.setAllClaims(allClaims);

        ClaimsCountValidatorException ex = assertThrows(ClaimsCountValidatorException.class, () -> claimsCountValidator.validate(claims));
        assertEquals(
            "Claims inválidas: apenas Name, Seed e Role são válidos. Enviado: " + allClaims, 
            ex.getMessage()
        );

        verify(mockLogger).info("Validating claims count");
        verify(mockLogger).error("Invalid claims count: {}", allClaims.size());
    }

    @Test
    public void testValidateWithLessThanExpectedClaims() {
        Claims claims = new Claims();
        Map<String, String> allClaims = new HashMap<>();
        allClaims.put("Name", "Nome");
        allClaims.put("Role", "Admin");
        claims.setAllClaims(allClaims);

        ClaimsCountValidatorException ex = assertThrows(ClaimsCountValidatorException.class, () -> claimsCountValidator.validate(claims));
        assertEquals(
            "Claims inválidas: apenas Name, Seed e Role são válidos. Enviado: " + allClaims, 
            ex.getMessage()
        );

        verify(mockLogger).info("Validating claims count");
        verify(mockLogger).error("Invalid claims count: {}", allClaims.size());
    }

    @Test
    public void testGetValidatorName() {
        assertEquals("ClaimsValidator", claimsCountValidator.getValidatorName());
    }
}
