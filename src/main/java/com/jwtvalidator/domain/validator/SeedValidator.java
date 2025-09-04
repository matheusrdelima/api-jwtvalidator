package com.jwtvalidator.domain.validator;

import java.util.Objects;

import com.jwtvalidator.core.logs.LoggerManager;
import com.jwtvalidator.core.validator.ClaimValidator;
import com.jwtvalidator.domain.exception.InvalidSeedException;
import com.jwtvalidator.domain.exception.SeedNotPrimeException;
import com.jwtvalidator.domain.model.Claims;
import com.jwtvalidator.infrastructure.logs.LoggerManagerImpl;
import com.jwtvalidator.shared.util.MathUtils;

public class SeedValidator implements ClaimValidator {

    public static final String VALIDATOR_NAME = "SeedValidator";

    private final LoggerManager<SeedValidator> logger = new LoggerManagerImpl<>(SeedValidator.class);

    @Override
    public void validate(Claims claims) {
        logger.info("Validating Seed claim");

        if (Objects.isNull(claims) || Objects.isNull(claims.getSeed())) {
            logger.error("Claim or Seed is null");
            throw new InvalidSeedException("Claim ou Seed nulo");
        }

        String seedString = claims.getSeed();

        try {
            long seed = Long.parseLong(seedString);

            boolean isPrime = MathUtils.isPrime(seed);

            if (!isPrime) {
                logger.error("Seed is not a prime number: {}", seed);
                throw new SeedNotPrimeException("Seed não é um número primo: " + seed);
            }
            
            logger.info("Seed claim validation passed");
        } catch (NumberFormatException e) {
            logger.error("Seed is not a valid number: {}", seedString);
            throw new InvalidSeedException("Não é um número válido: " + seedString);
        }
    }

    @Override
    public String getValidatorName() {
        return VALIDATOR_NAME;
    }
}
