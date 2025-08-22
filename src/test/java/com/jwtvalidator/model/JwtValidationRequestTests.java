package com.jwtvalidator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

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
