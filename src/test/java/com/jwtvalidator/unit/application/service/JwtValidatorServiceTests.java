package com.jwtvalidator.unit.application.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jwtvalidator.application.service.JwtValidatorServiceImpl;
import com.jwtvalidator.core.logs.LoggerManager;
import com.jwtvalidator.core.metrics.JwtMetrics;
import com.jwtvalidator.core.token.Decoder;
import com.jwtvalidator.core.validator.ClaimValidator;
import com.jwtvalidator.domain.mapper.ClaimsMapper;
import com.jwtvalidator.domain.model.Claims;

@ExtendWith(MockitoExtension.class)
public class JwtValidatorServiceTests {

    @Mock
    private Decoder decoder;

    @Mock
    private ClaimsMapper mapper;

    @Mock
    private ClaimValidator validator1;

    @Mock
    private ClaimValidator validator2;

    @Mock
    private JwtMetrics metrics;

    @Mock
    private Claims claims;

    @SuppressWarnings("unchecked")
    @Test
    public void testValidateCallsAllDependenciesIncludingLogger() throws Exception {
        String jwt = "jwt-token";

        Map<String, String> decodedMap = new HashMap<>();
        decodedMap.put("Name", "Matheus");
        decodedMap.put("Role", "admin");
        decodedMap.put("Seed", "7841");

        when(decoder.decode(jwt)).thenReturn(decodedMap);
        when(mapper.toClaims(decodedMap)).thenReturn(claims);

        JwtValidatorServiceImpl service = new JwtValidatorServiceImpl(
            decoder, mapper, List.of(validator1, validator2), metrics
        );

        LoggerManager<JwtValidatorServiceImpl> mockLogger = mock(LoggerManager.class);
        Field loggerField = JwtValidatorServiceImpl.class.getDeclaredField("logger");
        loggerField.setAccessible(true);
        loggerField.set(service, mockLogger);

        service.validate(jwt);

        verify(decoder).decode(jwt);
        verify(mapper).toClaims(decodedMap);
        verify(validator1).validate(claims);
        verify(validator2).validate(claims);
        verify(metrics).incrementValidationSuccessCount();

        verify(mockLogger).info("Starting JWT validation");
        verify(mockLogger).info("Decoded claims: {}", claims.getAllClaims());
        verify(mockLogger).info("JWT validation completed successfully");
    }
}
