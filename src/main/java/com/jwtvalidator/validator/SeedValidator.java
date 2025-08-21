package com.jwtvalidator.validator;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.jwtvalidator.core.validator.Validator;
import com.jwtvalidator.exception.InvalidSeedException;
import com.jwtvalidator.exception.SeedNotPrimeException;
import com.jwtvalidator.model.Claims;
import com.jwtvalidator.util.MathUtils;

@Component
public class SeedValidator implements Validator {

    public static final String VALIDATOR_NAME = "SeedValidator";

    @Override
    public Boolean validate(Claims claims) {
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

            return true;
        } catch (NumberFormatException e) {
            throw new InvalidSeedException("Não é um número válido: " + seedString);
        }
    }

    @Override
    public String getValidatorName() {
        return VALIDATOR_NAME;
    }
}
