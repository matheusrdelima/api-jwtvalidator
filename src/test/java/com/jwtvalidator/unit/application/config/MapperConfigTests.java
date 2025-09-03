package com.jwtvalidator.unit.application.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jwtvalidator.application.config.MapperConfig;
import com.jwtvalidator.domain.mapper.ClaimsMapper;

@ExtendWith(MockitoExtension.class)
public class MapperConfigTests {

    @Test
    public void testClaimsMapperBean() {
        MapperConfig mapperConfig = new MapperConfig();
        ClaimsMapper claimsMapper = mapperConfig.claimsMapper();

        assertNotNull(claimsMapper);
        assertTrue(claimsMapper instanceof ClaimsMapper);
    }
}
