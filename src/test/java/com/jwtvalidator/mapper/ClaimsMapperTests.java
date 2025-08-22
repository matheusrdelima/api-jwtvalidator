package com.jwtvalidator.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jwtvalidator.model.Claims;

@ExtendWith(MockitoExtension.class)
public class ClaimsMapperTests {

    @Test
    public void testclaimsMapper() {
        String name = "name";
        String role = "Admin";
        String seed = "2";

        Map<String, String> claimsMap = Map.of(
                "Name", name,
                "Role", role,
                "Seed", seed);

        ClaimsMapper claimsMapper = new ClaimsMapper();
        Claims claims = claimsMapper.toClaims(claimsMap);

        assertEquals(claims.getName(), name);
        assertEquals(claims.getRole(), role);
        assertEquals(claims.getSeed(), seed);
        assertEquals(claims.getAllClaims(), claimsMap);
    }

    @Test
    public void testclaimsMapperMalformedJwtException() {
        ClaimsMapper claimsMapper = new ClaimsMapper();

        assertThrows(com.jwtvalidator.exception.MalformedJwtException.class, () -> {
            claimsMapper.toClaims(null);
        });
    }
}
