package com.jwtvalidator.service.impl;

import org.springframework.stereotype.Service;

import com.jwtvalidator.service.JwtValidatorService;

@Service
public class JwtValidatorServiceImpl implements JwtValidatorService {

    @Override
    public Boolean validate(String jwt) {
        return true;
    }
}
