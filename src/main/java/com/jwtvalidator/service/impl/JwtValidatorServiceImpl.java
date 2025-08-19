package com.jwtvalidator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwtvalidator.model.Claims;
import com.jwtvalidator.service.JwtValidatorService;
import com.jwtvalidator.util.JWTDecode;
import com.jwtvalidator.validator.Validator;

@Service
public class JwtValidatorServiceImpl implements JwtValidatorService {

    private final List<Validator> validators;

    @Autowired
    public JwtValidatorServiceImpl(List<Validator> validators) {
        this.validators = validators;
    }

    @Override
    public Boolean validate(String jwt) {
        Claims claims = JWTDecode.decode(jwt);

        System.out.println("Claims: " + claims);

        for (Validator validator : validators) {
            Boolean isValid = validator.validate();

            if (!isValid) {
                System.out.println("Validação falhou, validador: " + validator.getValidatorName());
                return false;
            }
        }

        return true;
    }
}
