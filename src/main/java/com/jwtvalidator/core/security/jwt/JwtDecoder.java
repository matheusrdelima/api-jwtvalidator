package com.jwtvalidator.core.security.jwt;

import com.jwtvalidator.model.Claims;

public interface JwtDecoder {
    Claims decode(String token);
}
