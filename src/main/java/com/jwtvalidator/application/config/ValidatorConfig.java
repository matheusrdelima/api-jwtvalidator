package com.jwtvalidator.application.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jwtvalidator.core.validator.ClaimValidator;
import com.jwtvalidator.domain.validator.ClaimsCountValidator;
import com.jwtvalidator.domain.validator.NameValidator;
import com.jwtvalidator.domain.validator.RoleValidator;
import com.jwtvalidator.domain.validator.SeedValidator;

@Configuration
public class ValidatorConfig {

    @Bean
    public NameValidator nameValidator() {
        return new NameValidator();
    }

    @Bean
    public RoleValidator roleValidator() {
        return new RoleValidator();
    }

    @Bean
    public SeedValidator seedValidator() {
        return new SeedValidator();
    }

    @Bean
    public ClaimsCountValidator claimsCountValidator() {
        return new ClaimsCountValidator();
    }

    @Bean
    public List<ClaimValidator> validators(
            NameValidator name,
            RoleValidator role,
            SeedValidator seed,
            ClaimsCountValidator count) {
        return List.of(name, role, seed, count);
    }
}
