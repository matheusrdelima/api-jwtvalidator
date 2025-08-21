package com.jwtvalidator.core.validator;

import com.jwtvalidator.model.Claims;

public interface Validator {
    void validate(Claims claims);

    String getValidatorName();
}
