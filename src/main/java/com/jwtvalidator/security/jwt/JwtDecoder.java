package com.jwtvalidator.security.jwt;

import com.jwtvalidator.model.Claims;

public interface JwtDecoder {
    Claims decode(String token);
}
