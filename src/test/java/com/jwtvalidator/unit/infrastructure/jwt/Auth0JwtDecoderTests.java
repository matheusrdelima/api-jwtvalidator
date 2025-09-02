package com.jwtvalidator.unit.infrastructure.jwt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jwtvalidator.infrastructure.token.Auth0JwtDecoder;

@ExtendWith(MockitoExtension.class)
public class Auth0JwtDecoderTests {

    @InjectMocks
    private Auth0JwtDecoder decoder;

    @Test
    void testDecodeValidToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiTWF0aGV1cyIsInJvbGUiOiJhZG1pbiJ9.signature";

        DecodedJWT decoded = decoder.decode(token);

        assertNotNull(decoded);
        assertEquals("Matheus", decoded.getClaim("name").asString());
        assertEquals("admin", decoded.getClaim("role").asString());
    }

    @Test
    void testDecodeInvalidTokenThrowsException() {
        String invalidToken = "token_invalido";

        assertThrows(JWTDecodeException.class, () -> decoder.decode(invalidToken));
    }

    @Test
    void testDecodeNullTokenThrowsException() {
        assertThrows(JWTDecodeException.class, () -> decoder.decode(null));
    }
}
