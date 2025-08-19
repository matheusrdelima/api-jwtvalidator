package com.jwtvalidator.validator;

import org.springframework.stereotype.Component;

import com.jwtvalidator.model.Claims;

@Component
public class SeedValidator implements Validator {

    public static final String VALIDATOR_NAME = "SeedValidator";

    @Override
    public Boolean validate(Claims claims) {
        return true;
    }

    @Override
    public String getValidatorName() {
        return VALIDATOR_NAME;
    }
}
