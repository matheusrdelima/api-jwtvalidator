package com.jwtvalidator.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ClaimsTests {    
    
    @Test
    public void testclaims() {
        String name = "name";
        String role = "Admin";
        String seed = "2";

        Map<String, String> allClaims = Map.of(
            "name", name,
            "role", role,
            "seed", seed
        );

        Claims claims = new Claims();
        claims.setName(name);
        claims.setRole(role);
        claims.setSeed(seed);
        claims.setAllClaims(allClaims);

        assertEquals(name, claims.getName());
        assertEquals(role, claims.getRole());
        assertEquals(seed, claims.getSeed());
        assertEquals(allClaims, claims.getAllClaims());
    }
    
    @Test
    public void testclaimsWithParams() {
        String name = "name";
        String role = "Admin";
        String seed = "2";

        Map<String, String> allClaims = Map.of(
            "name", name,
            "role", role,
            "seed", seed
        );

        Claims claims = new Claims(name, role, seed, allClaims);

        assertEquals(name, claims.getName());
        assertEquals(role, claims.getRole());
        assertEquals(seed, claims.getSeed());
        assertEquals(allClaims, claims.getAllClaims());
    }
}
