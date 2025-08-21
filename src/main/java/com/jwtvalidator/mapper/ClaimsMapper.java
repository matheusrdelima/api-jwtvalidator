package com.jwtvalidator.mapper;

import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.jwtvalidator.exception.MalformedJwtException;
import com.jwtvalidator.model.Claims;

@Component
public class ClaimsMapper {

    public Claims toClaims(Map<String, String> claimsMap) {
        if (Objects.isNull(claimsMap)) {
            throw new MalformedJwtException("JWT não contém claims");
        }

        String name = claimsMap.get("Name");
        String role = claimsMap.get("Role");
        String seed = claimsMap.get("Seed");

        return new Claims(name, role, seed, claimsMap);
    }
}
