package com.jwtvalidator.domain.validator;

import java.util.Objects;

import com.jwtvalidator.core.validator.ClaimValidator;
import com.jwtvalidator.domain.exception.InvalidSeedException;
import com.jwtvalidator.domain.exception.SeedNotPrimeException;
import com.jwtvalidator.domain.model.Claims;
import com.jwtvalidator.shared.util.MathUtils;

public class SeedValidator implements ClaimValidator {

    public static final String VALIDATOR_NAME = "SeedValidator";

    @Override
    public void validate(Claims claims) {
        if (Objects.isNull(claims) || Objects.isNull(claims.getSeed())) {
            throw new InvalidSeedException("Claim ou Seed nulo");
        }

        String seedString = claims.getSeed();

        try {
            long seed = Long.parseLong(seedString);

            boolean isPrime = MathUtils.isPrime(seed);

            if (!isPrime) {
                throw new SeedNotPrimeException("Seed não é um número primo: " + seed);
            }
        } catch (NumberFormatException e) {
            throw new InvalidSeedException("Não é um número válido: " + seedString);
        }
    }

    @Override
    public String getValidatorName() {
        return VALIDATOR_NAME;
    }
}
