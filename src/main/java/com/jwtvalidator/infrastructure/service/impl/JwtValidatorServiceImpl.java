package com.jwtvalidator.infrastructure.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwtvalidator.core.security.jwt.JwtDecoder;
import com.jwtvalidator.core.service.JwtValidatorService;
import com.jwtvalidator.core.validator.Validator;
import com.jwtvalidator.exception.MalformedJwtException;
import com.jwtvalidator.model.Claims;

@Service
public class JwtValidatorServiceImpl implements JwtValidatorService {

    private final JwtDecoder jwtDecoder;
    private final List<Validator> validators;

    @Autowired
    public JwtValidatorServiceImpl(JwtDecoder jwtDecoder, List<Validator> validators) {
        this.jwtDecoder = jwtDecoder;
        this.validators = validators;
    }

    @Override
    public Boolean validate(String jwt) {
        Claims claims = jwtDecoder.decode(jwt);

        if (Objects.isNull(claims)) {
            throw new MalformedJwtException("JWT não decodificável");
        }

        for (Validator validator : validators) {
            validator.validate(claims);
        }

        return true;
    }
}
