package com.jwtvalidator.validator;

import org.springframework.stereotype.Component;

@Component
public class RoleValidator implements Validator {

    public static final String VALIDATOR_NAME = "RoleValidator";

    @Override
    public Boolean validate() {
        return true;
    }

    @Override
    public String getValidatorName() {
        return VALIDATOR_NAME;
    }
}
