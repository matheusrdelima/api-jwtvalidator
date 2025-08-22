package com.jwtvalidator.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jwtvalidator.exception.InvalidRoleException;
import com.jwtvalidator.model.Claims;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RoleValidatorTests {

    private RoleValidator roleValidator;

    @BeforeEach
    public void setUp() {
        roleValidator = new RoleValidator();
    }

    @Test
    public void testValidateWithValidRoleAdmin() {
        Claims claims = new Claims();
        claims.setRole("Admin");
        assertDoesNotThrow(() -> roleValidator.validate(claims));
    }

    @Test
    public void testValidateWithValidRoleMember() {
        Claims claims = new Claims();
        claims.setRole("Member");
        assertDoesNotThrow(() -> roleValidator.validate(claims));
    }

    @Test
    public void testValidateWithValidRoleExternal() {
        Claims claims = new Claims();
        claims.setRole("External");
        assertDoesNotThrow(() -> roleValidator.validate(claims));
    }

    @Test
    public void testValidateWithNullClaims() {
        assertThrows(InvalidRoleException.class, () -> roleValidator.validate(null));
    }

    @Test
    public void testValidateWithNullRole() {
        Claims claims = new Claims();
        claims.setRole(null);
        assertThrows(InvalidRoleException.class, () -> roleValidator.validate(claims));
    }

    @Test
    public void testValidateWithInvalidRole() {
        Claims claims = new Claims();
        claims.setRole("User");
        InvalidRoleException ex = assertThrows(InvalidRoleException.class, () -> roleValidator.validate(claims));
        assertEquals("Role inválida: User", ex.getMessage());
    }

    @Test
    public void testGetValidatorName() {
        assertEquals("RoleValidator", roleValidator.getValidatorName());
    }
}