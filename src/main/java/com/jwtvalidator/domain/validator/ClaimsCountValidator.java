package com.jwtvalidator.domain.validator;

import java.util.Objects;

import com.jwtvalidator.core.logs.LoggerManager;
import com.jwtvalidator.core.validator.ClaimValidator;
import com.jwtvalidator.domain.exception.ClaimsCountValidatorException;
import com.jwtvalidator.domain.exception.InvalidClaimsException;
import com.jwtvalidator.domain.model.Claims;
import com.jwtvalidator.infrastructure.logs.LoggerManagerImpl;

public class ClaimsCountValidator implements ClaimValidator {

    public static final String VALIDATOR_NAME = "ClaimsValidator";
    private static final int EXPECTED_CLAIMS_COUNT = 3;
    private final LoggerManager<ClaimsCountValidator> logger = new LoggerManagerImpl<>(ClaimsCountValidator.class);

    @Override
    public void validate(Claims claims) {
        logger.info("Validating claims count");

        if (Objects.isNull(claims) || Objects.isNull(claims.getAllClaims()) || claims.getAllClaims().isEmpty()) {
            logger.error("Claims or allClaims is null or empty");
            throw new InvalidClaimsException("Claims ou allClaims é nulo");
        }

        int claimsSize = claims.getAllClaims().size();

        boolean isValid = claimsSize == EXPECTED_CLAIMS_COUNT;

        if (!isValid) {
            logger.error("Invalid claims count: {}", claimsSize);
            throw new ClaimsCountValidatorException(
                    "Claims inválidas: apenas Name, Seed e Role são válidos. Enviado: " + claims.getAllClaims());
        }

        logger.info("Claims count validation passed");
    }

    @Override
    public String getValidatorName() {
        return VALIDATOR_NAME;
    }
}
