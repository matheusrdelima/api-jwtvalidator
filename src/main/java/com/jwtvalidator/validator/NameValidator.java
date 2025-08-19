package com.jwtvalidator.validator;

import org.springframework.stereotype.Component;

@Component
public class NameValidator implements Validator {

    public static final String VALIDATOR_NAME = "NameValidator";

    @Override
    public Boolean validate() {
        return true;
    }

    @Override
    public String getValidatorName() {
        return VALIDATOR_NAME;
    }
}
