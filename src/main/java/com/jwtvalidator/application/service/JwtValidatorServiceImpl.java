package com.jwtvalidator.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jwtvalidator.core.metrics.JwtMetrics;
import com.jwtvalidator.core.service.JwtValidatorService;
import com.jwtvalidator.core.token.Decoder;
import com.jwtvalidator.core.validator.ClaimValidator;
import com.jwtvalidator.domain.mapper.ClaimsMapper;
import com.jwtvalidator.domain.model.Claims;

@Service
public class JwtValidatorServiceImpl implements JwtValidatorService {

    private final Decoder decoder;
    private final ClaimsMapper mapper;
    private final List<ClaimValidator> validators;
    private final JwtMetrics metrics;

    public JwtValidatorServiceImpl(Decoder decoder, ClaimsMapper mapper, List<ClaimValidator> validators, JwtMetrics metrics) {
        this.decoder = decoder;
        this.mapper = mapper;
        this.validators = validators;
        this.metrics = metrics;
    }

    @Override
    public void validate(String jwt) {
        Claims claims = mapper.toClaims(decoder.decode(jwt));

        validators.forEach(validator -> validator.validate(claims));

        metrics.incrementValidationSuccessCount();
    }
}
