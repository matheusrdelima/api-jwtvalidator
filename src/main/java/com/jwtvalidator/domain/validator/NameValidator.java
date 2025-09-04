package com.jwtvalidator.domain.validator;

import java.util.Objects;

import com.jwtvalidator.core.logs.LoggerManager;
import com.jwtvalidator.core.validator.ClaimValidator;
import com.jwtvalidator.domain.exception.InvalidNameException;
import com.jwtvalidator.domain.model.Claims;
import com.jwtvalidator.infrastructure.logs.LoggerManagerImpl;
import com.jwtvalidator.shared.util.StringUtils;

public class NameValidator implements ClaimValidator {

    public static final String VALIDATOR_NAME = "NameValidator";
    private static final int MAX_NAME_LENGTH = 256;

    private final LoggerManager<NameValidator> logger = new LoggerManagerImpl<>(NameValidator.class);

    @Override
    public void validate(Claims claims) {
        logger.info("Validating Name claim");

        if (Objects.isNull(claims) || Objects.isNull(claims.getName())) {
            logger.error("Claim or Name is null");
            throw new InvalidNameException("Claim ou Name nulo");
        }

        String name = claims.getName();

        if (name.length() > MAX_NAME_LENGTH) {
            logger.error("Name exceeds maximum length: {}", name.length());
            throw new InvalidNameException(
                    "Name maior que o tamanho permitido de caracteres, quantidade atual: " + name.length()
                            + " quantidade maxima: " + MAX_NAME_LENGTH);
        }

        if (StringUtils.hasNumber(name)) {
            logger.error("Name contains numbers: {}", name);
            throw new InvalidNameException("Name contém números: " + name);
        }

        logger.info("Name claim validation passed");
    }

    @Override
    public String getValidatorName() {
        return VALIDATOR_NAME;
    }
}
