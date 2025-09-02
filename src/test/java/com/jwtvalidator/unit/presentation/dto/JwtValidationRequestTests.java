package com.jwtvalidator.unit.presentation.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jwtvalidator.presentation.dto.JwtValidationRequest;

@ExtendWith(MockitoExtension.class)
public class JwtValidationRequestTests {

    @Test
    public void testsjwtValidationRequest() {
        String token = "token_jwt";

        JwtValidationRequest jwtValidationRequest = new JwtValidationRequest();
        jwtValidationRequest.setJwt(token);

        assertEquals(token, jwtValidationRequest.getJwt());
    }
}
