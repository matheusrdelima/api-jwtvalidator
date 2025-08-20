package com.jwtvalidator.validator;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.jwtvalidator.model.Claims;
import com.jwtvalidator.util.MathUtils;

@Component
public class SeedValidator implements Validator {

    public static final String VALIDATOR_NAME = "SeedValidator";

    @Override
    public Boolean validate(Claims claims) {
        if (Objects.isNull(claims) || Objects.isNull(claims.getSeed())) {
            System.out.println("Claim ou Seed nulo");
            return false;
        }

        String seedString = claims.getSeed();

        try {
            long seed = Long.parseLong(seedString);

            System.out.println("Seed: " + claims.getSeed());

            return MathUtils.isPrime(seed);
        } catch (NumberFormatException e) {
            System.out.println("Nao é um número válido: " + seedString);
            return false;
        }
    }

    @Override
    public String getValidatorName() {
        return VALIDATOR_NAME;
    }
}
