package com.jwtvalidator.core.validator;

import com.jwtvalidator.model.Claims;

public interface Validator {
    Boolean validate(Claims claims);

    String getValidatorName();
}
