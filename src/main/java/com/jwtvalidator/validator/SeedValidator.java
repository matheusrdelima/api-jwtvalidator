package com.jwtvalidator.validator;

import org.springframework.stereotype.Component;

@Component
public class SeedValidator implements Validator {

    public static final String VALIDATOR_NAME = "SeedValidator";

    @Override
    public Boolean validate() {
        return true;
    }

    @Override
    public String getValidatorName() {
        return VALIDATOR_NAME;
    }
}
