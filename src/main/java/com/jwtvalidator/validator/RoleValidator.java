package com.jwtvalidator.validator;

import java.util.Objects;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.jwtvalidator.core.validator.Validator;
import com.jwtvalidator.exception.InvalidRoleException;
import com.jwtvalidator.model.Claims;

@Component
public class RoleValidator implements Validator {

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
