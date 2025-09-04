package com.jwtvalidator.unit.domain.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.jwtvalidator.core.logs.LoggerManager;
import com.jwtvalidator.domain.exception.InvalidRoleException;
import com.jwtvalidator.domain.model.Claims;
import com.jwtvalidator.domain.validator.RoleValidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

@ExtendWith(MockitoExtension.class)
public class RoleValidatorTests {

    private RoleValidator roleValidator;
    private LoggerManager<RoleValidator> mockLogger;

    @SuppressWarnings("unchecked")
    @BeforeEach
    public void setUp() throws Exception {
        roleValidator = new RoleValidator();

        mockLogger = mock(LoggerManager.class);

        Field loggerField = RoleValidator.class.getDeclaredField("logger");
        loggerField.setAccessible(true);
        loggerField.set(roleValidator, mockLogger);
    }

    @Test
    public void testValidateWithValidRoleAdmin() {
        Claims claims = new Claims();
        claims.setRole("Admin");

        assertDoesNotThrow(() -> roleValidator.validate(claims));

        verify(mockLogger).info("Validating Role claim");
        verify(mockLogger).info("Role claim validation passed");
    }

    @Test
    public void testValidateWithValidRoleMember() {
        Claims claims = new Claims();
        claims.setRole("Member");

        assertDoesNotThrow(() -> roleValidator.validate(claims));

        verify(mockLogger).info("Validating Role claim");
        verify(mockLogger).info("Role claim validation passed");
    }

    @Test
    public void testValidateWithValidRoleExternal() {
        Claims claims = new Claims();
        claims.setRole("External");

        assertDoesNotThrow(() -> roleValidator.validate(claims));

        verify(mockLogger).info("Validating Role claim");
        verify(mockLogger).info("Role claim validation passed");
    }

    @Test
    public void testValidateWithNullClaims() {
        assertThrows(InvalidRoleException.class, () -> roleValidator.validate(null));

        verify(mockLogger).info("Validating Role claim");
        verify(mockLogger).error("Claim or Role is null");
    }

    @Test
    public void testValidateWithNullRole() {
        Claims claims = new Claims();
        claims.setRole(null);

        assertThrows(InvalidRoleException.class, () -> roleValidator.validate(claims));

        verify(mockLogger).info("Validating Role claim");
        verify(mockLogger).error("Claim or Role is null");
    }

    @Test
    public void testValidateWithInvalidRole() {
        Claims claims = new Claims();
        claims.setRole("User");

        InvalidRoleException ex = assertThrows(InvalidRoleException.class, () -> roleValidator.validate(claims));
        assertEquals("Role inválida: User", ex.getMessage());

        verify(mockLogger).info("Validating Role claim");
        verify(mockLogger).error("Invalid Role: {}", "User");
    }

    @Test
    public void testGetValidatorName() {
        assertEquals("RoleValidator", roleValidator.getValidatorName());
    }
}
