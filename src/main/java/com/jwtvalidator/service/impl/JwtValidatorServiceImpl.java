package com.jwtvalidator.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwtvalidator.model.Claims;
import com.jwtvalidator.security.jwt.JwtDecoder;
import com.jwtvalidator.service.JwtValidatorService;
import com.jwtvalidator.validator.Validator;

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
            System.out.println("Falha ao decodificar token");
            return false;
        }

        for (Validator validator : validators) {
            Boolean isValid = validator.validate(claims);

            if (!isValid) {
                System.out.println("Validação falhou, validador: " + validator.getValidatorName());
                return false;
            }
        }

        return true;
    }
}
