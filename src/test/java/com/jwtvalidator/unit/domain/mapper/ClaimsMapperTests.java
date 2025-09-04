package com.jwtvalidator.unit.domain.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Field;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jwtvalidator.core.logs.LoggerManager;
import com.jwtvalidator.domain.exception.MalformedJwtException;
import com.jwtvalidator.domain.mapper.ClaimsMapper;
import com.jwtvalidator.domain.model.Claims;

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

    @SuppressWarnings("unchecked")
    @Test
    public void testClaimsMapperMalformedJwtExceptionWithLogger() throws Exception {
        LoggerManager<ClaimsMapper> mockLogger = mock(LoggerManager.class);

        ClaimsMapper claimsMapper = new ClaimsMapper();

        Field loggerField = ClaimsMapper.class.getDeclaredField("logger");
        loggerField.setAccessible(true);
        loggerField.set(claimsMapper, mockLogger);

        assertThrows(MalformedJwtException.class, () -> claimsMapper.toClaims(null));

        verify(mockLogger).error("Claims map is null");
    }
}
