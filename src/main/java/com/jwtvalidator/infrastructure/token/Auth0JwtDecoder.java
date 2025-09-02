package com.jwtvalidator.infrastructure.token;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jwtvalidator.core.token.JwtDecoder;

@Component
public class Auth0JwtDecoder implements JwtDecoder {

    @Override
    public DecodedJWT decode(String token) {
        return JWT.decode(token);
    }
}
