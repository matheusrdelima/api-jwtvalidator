package com.jwtvalidator.unit.application.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jwtvalidator.application.config.ValidatorConfig;
import com.jwtvalidator.core.validator.ClaimValidator;
import com.jwtvalidator.domain.validator.ClaimsCountValidator;
import com.jwtvalidator.domain.validator.NameValidator;
import com.jwtvalidator.domain.validator.RoleValidator;
import com.jwtvalidator.domain.validator.SeedValidator;

@ExtendWith(MockitoExtension.class)
public class ValidatorConfigTests {

    @Test
    public void testNameValidatorBean() {
        ValidatorConfig config = new ValidatorConfig();
        NameValidator validator = config.nameValidator();
        assertNotNull(validator);
        assertTrue(validator instanceof NameValidator);
    }

    @Test
    public void testRoleValidatorBean() {
        ValidatorConfig config = new ValidatorConfig();
        RoleValidator validator = config.roleValidator();
        assertNotNull(validator);
        assertTrue(validator instanceof RoleValidator);
    }

    @Test
    public void testSeedValidatorBean() {
        ValidatorConfig config = new ValidatorConfig();
        SeedValidator validator = config.seedValidator();
        assertNotNull(validator);
        assertTrue(validator instanceof SeedValidator);
    }

    @Test
    public void testClaimsCountValidatorBean() {
        ValidatorConfig config = new ValidatorConfig();
        ClaimsCountValidator validator = config.claimsCountValidator();
        assertNotNull(validator);
        assertTrue(validator instanceof ClaimsCountValidator);
    }

    @Test
    public void testValidatorsBean() {
        ValidatorConfig config = new ValidatorConfig();
        NameValidator name = config.nameValidator();
        RoleValidator role = config.roleValidator();
        SeedValidator seed = config.seedValidator();
        ClaimsCountValidator count = config.claimsCountValidator();

        List<ClaimValidator> validators = config.validators(name, role, seed, count);

        assertNotNull(validators);
        assertEquals(4, validators.size());
        assertTrue(validators.contains(name));
        assertTrue(validators.contains(role));
        assertTrue(validators.contains(seed));
        assertTrue(validators.contains(count));
    }
}
