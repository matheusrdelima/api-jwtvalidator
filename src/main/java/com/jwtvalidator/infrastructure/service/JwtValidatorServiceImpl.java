package com.jwtvalidator.infrastructure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwtvalidator.core.metrics.JwtMetrics;
import com.jwtvalidator.core.security.jwt.JwtDecoder;
import com.jwtvalidator.core.service.JwtValidatorService;
import com.jwtvalidator.core.validator.Validator;
import com.jwtvalidator.mapper.ClaimsMapper;
import com.jwtvalidator.model.Claims;

@Service
public class JwtValidatorServiceImpl implements JwtValidatorService {

    private final JwtDecoder jwtDecoder;
    private final ClaimsMapper claimsMapper;
    private final List<Validator> validators;
    private final JwtMetrics jwtMetrics;

    @Autowired
    public JwtValidatorServiceImpl(JwtDecoder jwtDecoder, ClaimsMapper claimsMapper, List<Validator> validators, JwtMetrics jwtMetrics) {
        this.jwtDecoder = jwtDecoder;
        this.claimsMapper = claimsMapper;
        this.validators = validators;
        this.jwtMetrics = jwtMetrics;
    }

    @Override
    public void validate(String jwt) {
        Claims claims = claimsMapper.toClaims(jwtDecoder.decode(jwt));

        for (Validator validator : validators) {
            validator.validate(claims);
        }

        jwtMetrics.incrementValidationSuccessCount();
    }
}
