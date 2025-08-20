package com.jwtvalidator.security.jwt.impl;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jwtvalidator.model.Claims;
import com.jwtvalidator.security.jwt.JwtDecoder;

@Component
public class JwtDecoderImpl implements JwtDecoder {

    @Override
    public Claims decode(String token) {
        try {
            DecodedJWT decoded = JWT.decode(token);

            String name = decoded.getClaim("Name").asString();
            String role = decoded.getClaim("Role").asString();
            String seed = decoded.getClaim("Seed").asString();

            return new Claims(name, role, seed);
        } catch (JWTDecodeException e) {
            System.out.println(e);
            return null;
        }
    }
}
