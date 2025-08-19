package com.jwtvalidator.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jwtvalidator.model.Claims;

public class JWTDecode {
    public static Claims decode(String token) {
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
