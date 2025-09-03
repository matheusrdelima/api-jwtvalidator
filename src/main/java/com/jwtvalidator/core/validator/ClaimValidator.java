package com.jwtvalidator.core.validator;

import com.jwtvalidator.domain.model.Claims;

public interface ClaimValidator {
    void validate(Claims claims);

    String getValidatorName();
}
