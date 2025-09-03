package com.jwtvalidator.core.token;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface JwtDecoder {
    DecodedJWT decode(String token);
}
