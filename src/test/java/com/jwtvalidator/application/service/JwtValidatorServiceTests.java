package com.jwtvalidator.application.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Test
    public void testValidateCallsAllDependencies() {
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

        service.validate(jwt);

        verify(decoder).decode(jwt);
        verify(mapper).toClaims(decodedMap);
        verify(validator1).validate(claims);
        verify(validator2).validate(claims);
        verify(metrics).incrementValidationSuccessCount();
    }    
}
