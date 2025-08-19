package com.jwtvalidator.validator;

import java.util.Objects;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.jwtvalidator.model.Claims;

@Component
public class RoleValidator implements Validator {

    public static final String VALIDATOR_NAME = "RoleValidator";
    private static final Set<String> ROLES = Set.of("Admin", "Member", "External");

    @Override
    public Boolean validate(Claims claims) {
        if (Objects.isNull(claims) || Objects.isNull(claims.getRole())) {
            System.out.println("Claim ou Role nulo");
            return false;
        }

        System.out.println("Role: " + claims.getRole());

        return ROLES.contains(claims.getRole());
    }

    @Override
    public String getValidatorName() {
        return VALIDATOR_NAME;
    }
}
