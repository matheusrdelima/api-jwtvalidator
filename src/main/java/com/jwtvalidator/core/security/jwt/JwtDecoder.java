package com.jwtvalidator.core.security.jwt;

import java.util.Map;

public interface JwtDecoder {
    Map<String, String> decode(String token);
}
