package com.jwtvalidator.infrastructure.token;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jwtvalidator.core.logs.LoggerManager;
import com.jwtvalidator.core.token.Decoder;
import com.jwtvalidator.core.token.JwtDecoder;
import com.jwtvalidator.domain.exception.MalformedJwtException;
import com.jwtvalidator.infrastructure.logs.LoggerManagerImpl;

@Component
public class DecoderImpl implements Decoder {

    private final JwtDecoder jwtDecoder;
    private final LoggerManager<DecoderImpl> logger = new LoggerManagerImpl<>(DecoderImpl.class);;

    public DecoderImpl(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public Map<String, String> decode(String token) {
        try {
            logger.info("Decoding JWT token");

            DecodedJWT decoded = jwtDecoder.decode(token);

            Map<String, String> claimsMap = new HashMap<>();

            decoded.getClaims().forEach((key, claim) -> {
                if (Objects.nonNull(claim) && Objects.nonNull(claim.asString())) {
                    claimsMap.put(key, claim.asString());
                }
            });

            logger.info("JWT token decoded successfully");

            return claimsMap;
        } catch (JWTDecodeException e) {
            logger.error("Failed to decode JWT token", e.getMessage());
            throw new MalformedJwtException("JWT não decodificável");
        }
    }
}
