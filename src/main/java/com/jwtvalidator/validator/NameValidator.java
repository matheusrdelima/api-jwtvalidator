package com.jwtvalidator.validator;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.jwtvalidator.exception.InvalidNameException;
import com.jwtvalidator.model.Claims;
import com.jwtvalidator.util.StringUtils;

@Component
public class NameValidator implements Validator {

    public static final String VALIDATOR_NAME = "NameValidator";
    private static final int MAX_NAME_LENGTH = 256;

    @Override
    public Boolean validate(Claims claims) {
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

        return true;
    }

    @Override
    public String getValidatorName() {
        return VALIDATOR_NAME;
    }
}
