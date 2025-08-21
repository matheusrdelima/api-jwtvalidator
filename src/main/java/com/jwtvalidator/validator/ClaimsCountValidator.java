package com.jwtvalidator.validator;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.jwtvalidator.core.validator.Validator;
import com.jwtvalidator.exception.ClaimsCountValidatorException;
import com.jwtvalidator.exception.InvalidClaimsException;
import com.jwtvalidator.model.Claims;

@Component
public class ClaimsCountValidator implements Validator {

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
