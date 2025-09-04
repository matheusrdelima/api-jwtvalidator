package com.jwtvalidator.domain.validator;

import java.util.Objects;
import java.util.Set;

import com.jwtvalidator.core.logs.LoggerManager;
import com.jwtvalidator.core.validator.ClaimValidator;
import com.jwtvalidator.domain.exception.InvalidRoleException;
import com.jwtvalidator.domain.model.Claims;
import com.jwtvalidator.infrastructure.logs.LoggerManagerImpl;

public class RoleValidator implements ClaimValidator {

    public static final String VALIDATOR_NAME = "RoleValidator";
    private static final Set<String> ROLES = Set.of("Admin", "Member", "External");

    private final LoggerManager<RoleValidator> logger = new LoggerManagerImpl<>(RoleValidator.class);

    @Override
    public void validate(Claims claims) {
        logger.info("Validating Role claim");

        if (Objects.isNull(claims) || Objects.isNull(claims.getRole())) {
            logger.error("Claim or Role is null");
            throw new InvalidRoleException("Claim ou Role nulo");
        }

        if (!ROLES.contains(claims.getRole())) {
            logger.error("Invalid Role: {}", claims.getRole());
            throw new InvalidRoleException("Role inválida: " + claims.getRole());
        }

        logger.info("Role claim validation passed");
    }

    @Override
    public String getValidatorName() {
        return VALIDATOR_NAME;
    }
}
