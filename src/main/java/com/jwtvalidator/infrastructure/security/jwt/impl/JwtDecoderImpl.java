package com.jwtvalidator.infrastructure.security.jwt.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jwtvalidator.core.security.jwt.JwtDecoder;
import com.jwtvalidator.exception.MalformedJwtException;

@Component
public class JwtDecoderImpl implements JwtDecoder {

    @Override
    public Map<String, String> decode(String token) {
        try {
            DecodedJWT decoded = JWT.decode(token);

            Map<String, String> claimsMap = new HashMap<>();

            decoded.getClaims().forEach((key, claim) -> {
                if (Objects.nonNull(claim)) {
                    claimsMap.put(key, claim.asString());
                }
            });

            return claimsMap;
        } catch (JWTDecodeException e) {
            throw new MalformedJwtException("JWT não decodificável");
        }
    }
}
