package com.jwtvalidator.domain.validator;

import java.util.Objects;
import java.util.Set;

import com.jwtvalidator.core.validator.ClaimValidator;
import com.jwtvalidator.domain.exception.InvalidRoleException;
import com.jwtvalidator.domain.model.Claims;

public class RoleValidator implements ClaimValidator {

    public static final String VALIDATOR_NAME = "RoleValidator";
    private static final Set<String> ROLES = Set.of("Admin", "Member", "External");

    @Override
    public void validate(Claims claims) {
        if (Objects.isNull(claims) || Objects.isNull(claims.getRole())) {
            throw new InvalidRoleException("Claim ou Role nulo");
        }

        if (!ROLES.contains(claims.getRole())) {
            throw new InvalidRoleException("Role inválida: " + claims.getRole());
        }
    }

    @Override
    public String getValidatorName() {
        return VALIDATOR_NAME;
    }
}
