package com.jwtvalidator.domain.mapper;

import java.util.Map;
import java.util.Objects;

import com.jwtvalidator.domain.exception.MalformedJwtException;
import com.jwtvalidator.domain.model.Claims;

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
