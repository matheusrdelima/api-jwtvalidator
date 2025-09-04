package com.jwtvalidator.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jwtvalidator.core.logs.LoggerManager;
import com.jwtvalidator.core.metrics.JwtMetrics;
import com.jwtvalidator.core.service.JwtValidatorService;
import com.jwtvalidator.core.token.Decoder;
import com.jwtvalidator.core.validator.ClaimValidator;
import com.jwtvalidator.domain.mapper.ClaimsMapper;
import com.jwtvalidator.domain.model.Claims;
import com.jwtvalidator.infrastructure.logs.LoggerManagerImpl;

@Service
public class JwtValidatorServiceImpl implements JwtValidatorService {

    private final Decoder decoder;
    private final ClaimsMapper mapper;
    private final List<ClaimValidator> validators;
    private final JwtMetrics metrics;

    private final LoggerManager<JwtValidatorServiceImpl> logger = new LoggerManagerImpl<>(JwtValidatorServiceImpl.class);

    public JwtValidatorServiceImpl(Decoder decoder, ClaimsMapper mapper, List<ClaimValidator> validators, JwtMetrics metrics) {
        this.decoder = decoder;
        this.mapper = mapper;
        this.validators = validators;
        this.metrics = metrics;
    }

    @Override
    public void validate(String jwt) {
        logger.info("Starting JWT validation");

        Claims claims = mapper.toClaims(decoder.decode(jwt));

        logger.info("Decoded claims: {}", claims.getAllClaims());

        validators.forEach(validator -> validator.validate(claims));

        logger.info("JWT validation completed successfully");

        metrics.incrementValidationSuccessCount();
    }
}
