package com.jwtvalidator.domain.validator;

import java.util.Objects;

import com.jwtvalidator.core.validator.ClaimValidator;
import com.jwtvalidator.domain.exception.ClaimsCountValidatorException;
import com.jwtvalidator.domain.exception.InvalidClaimsException;
import com.jwtvalidator.domain.model.Claims;

public class ClaimsCountValidator implements ClaimValidator {

    public static final String VALIDATOR_NAME = "ClaimsValidator";
    private static final int EXPECTED_CLAIMS_COUNT = 3;

    @Override
    public void validate(Claims claims) {
        if (Objects.isNull(claims) || Objects.isNull(claims.getAllClaims()) || claims.getAllClaims().isEmpty()) {
            throw new InvalidClaimsException("Claims ou allClaims é nulo");
        }

        int claimsSize = claims.getAllClaims().size();

        boolean isValid = claimsSize == EXPECTED_CLAIMS_COUNT;

        if (!isValid) {
            throw new ClaimsCountValidatorException(
                    "Claims inválidas: apenas Name, Seed e Role são válidos. Enviado: " + claims.getAllClaims());
        }
    }

    @Override
    public String getValidatorName() {
        return VALIDATOR_NAME;
    }
}
