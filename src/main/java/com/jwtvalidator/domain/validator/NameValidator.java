package com.jwtvalidator.domain.validator;

import java.util.Objects;

import com.jwtvalidator.core.validator.ClaimValidator;
import com.jwtvalidator.domain.exception.InvalidNameException;
import com.jwtvalidator.domain.model.Claims;
import com.jwtvalidator.shared.util.StringUtils;

public class NameValidator implements ClaimValidator {

    public static final String VALIDATOR_NAME = "NameValidator";
    private static final int MAX_NAME_LENGTH = 256;

    @Override
    public void validate(Claims claims) {
        if (Objects.isNull(claims) || Objects.isNull(claims.getName())) {
            throw new InvalidNameException("Claim ou Name nulo");
        }

        String name = claims.getName();

        if (name.length() > MAX_NAME_LENGTH) {
            throw new InvalidNameException(
                    "Name maior que o tamanho permitido de caracteres, quantidade atual: " + name.length()
                            + " quantidade maxima: " + MAX_NAME_LENGTH);
        }

        if (StringUtils.hasNumber(name)) {
            throw new InvalidNameException("Name contém números: " + name);
        }
    }

    @Override
    public String getValidatorName() {
        return VALIDATOR_NAME;
    }
}
