package com.jwtvalidator.infrastructure.token;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jwtvalidator.core.token.Decoder;
import com.jwtvalidator.core.token.JwtDecoder;
import com.jwtvalidator.domain.exception.MalformedJwtException;

@Component
public class DecoderImpl implements Decoder {

    private final JwtDecoder jwtDecoder;

    public DecoderImpl(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public Map<String, String> decode(String token) {
        try {
            DecodedJWT decoded = jwtDecoder.decode(token);

            Map<String, String> claimsMap = new HashMap<>();

            decoded.getClaims().forEach((key, claim) -> {
                if (Objects.nonNull(claim) && Objects.nonNull(claim.asString())) {
                    claimsMap.put(key, claim.asString());
                }
            });

            return claimsMap;
        } catch (JWTDecodeException e) {
            throw new MalformedJwtException("JWT não decodificável");
        }
    }
}
