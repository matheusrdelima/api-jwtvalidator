package com.jwtvalidator.presentation.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwtvalidator.core.service.JwtValidatorService;
import com.jwtvalidator.presentation.dto.JwtValidationRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class JwtValidatorControllerTests {

    @Mock
    private JwtValidatorService jwtValidatorService;

    @InjectMocks
    private JwtValidatorController jwtValidatorController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(jwtValidatorController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testValidateEndpointReturnsOk() throws Exception {
        JwtValidationRequest request = new JwtValidationRequest();
        request.setJwt("token.jwt.aqui");

        doNothing().when(jwtValidatorService).validate("token.jwt.aqui");

        mockMvc.perform(post("/api/v1/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("verdadeiro"));

        verify(jwtValidatorService, times(1)).validate("token.jwt.aqui");
    }
}