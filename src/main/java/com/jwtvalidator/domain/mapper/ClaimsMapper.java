package com.jwtvalidator.domain.mapper;

import java.util.Map;
import java.util.Objects;

import com.jwtvalidator.core.logs.LoggerManager;
import com.jwtvalidator.domain.exception.MalformedJwtException;
import com.jwtvalidator.domain.model.Claims;
import com.jwtvalidator.infrastructure.logs.LoggerManagerImpl;

public class ClaimsMapper {

    private final LoggerManager<ClaimsMapper> logger = new LoggerManagerImpl<>(ClaimsMapper.class);

    public Claims toClaims(Map<String, String> claimsMap) {
        if (Objects.isNull(claimsMap)) {
            logger.error("Claims map is null");
            throw new MalformedJwtException("JWT não contém claims");
        }

        String name = claimsMap.get("Name");
        String role = claimsMap.get("Role");
        String seed = claimsMap.get("Seed");

        return new Claims(name, role, seed, claimsMap);
    }
}
