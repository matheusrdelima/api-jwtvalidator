package com.jwtvalidator.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jwtvalidator.domain.mapper.ClaimsMapper;

@Configuration
public class MapperConfig {

    @Bean
    public ClaimsMapper claimsMapper() {
        return new ClaimsMapper();
    }
}
