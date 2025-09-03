package com.jwtvalidator.unit.infrastructure.jwt;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jwtvalidator.core.token.JwtDecoder;
import com.jwtvalidator.domain.exception.MalformedJwtException;
import com.jwtvalidator.infrastructure.token.DecoderImpl;

@ExtendWith(MockitoExtension.class)
class DecoderTests {

    @Mock
    private JwtDecoder jwtDecoder;

    @InjectMocks
    private DecoderImpl decoder;

    @Test
    void testDecodeValidToken() {
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        Claim nameClaim = mock(Claim.class);
        Claim roleClaim = mock(Claim.class);

        when(nameClaim.asString()).thenReturn("Matheus");
        when(roleClaim.asString()).thenReturn("admin");
        when(decodedJWT.getClaims()).thenReturn(Map.of("name", nameClaim, "role", roleClaim));
        when(jwtDecoder.decode("token_valido")).thenReturn(decodedJWT);

        Map<String, String> claims = decoder.decode("token_valido");

        assertNotNull(claims);
        assertEquals(2, claims.size());
        assertEquals("Matheus", claims.get("name"));
        assertEquals("admin", claims.get("role"));
    }

    @Test
    void testDecodeTokenWithNullClaim() {
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        Claim emptyClaim = mock(Claim.class);

        when(emptyClaim.asString()).thenReturn(null);
        when(decodedJWT.getClaims()).thenReturn(Map.of("empty", emptyClaim));
        when(jwtDecoder.decode("token_com_claim_nulo")).thenReturn(decodedJWT);

        Map<String, String> claims = decoder.decode("token_com_claim_nulo");

        assertNotNull(claims);
        assertEquals(0, claims.size());
    }

    @Test
    void testDecodeTokenWithNoClaims() {
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaims()).thenReturn(Map.of());
        when(jwtDecoder.decode("token_sem_claims")).thenReturn(decodedJWT);

        Map<String, String> claims = decoder.decode("token_sem_claims");

        assertNotNull(claims);
        assertTrue(claims.isEmpty());
    }

    @Test
    void testDecodeMalformedTokenThrowsException() {
        when(jwtDecoder.decode("token_invalido")).thenThrow(new JWTDecodeException("erro"));

        assertThrows(MalformedJwtException.class, () -> decoder.decode("token_invalido"));
    }

    @Test
    void testDecoderImplConstructorNotNull() {
        DecoderImpl decoderImpl = new DecoderImpl(jwtDecoder);
        assertNotNull(decoderImpl);
    }

    @Test
    void testDecodeTokenWithReallyNullClaim() {
        DecodedJWT decodedJWT = mock(DecodedJWT.class);

        Map<String, Claim> claimsMap = new HashMap<>();
        claimsMap.put("empty", null); // aqui sim claim == null

        when(decodedJWT.getClaims()).thenReturn(claimsMap);
        when(jwtDecoder.decode("token_claim_nulo")).thenReturn(decodedJWT);

        Map<String, String> claims = decoder.decode("token_claim_nulo");

        assertNotNull(claims);
        assertTrue(claims.isEmpty());
    }

    @Test
    void testDecodeTokenWithClaimValueNull() {
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        Claim emptyClaim = mock(Claim.class);

        when(emptyClaim.asString()).thenReturn(null);
        when(decodedJWT.getClaims()).thenReturn(Map.of("empty", emptyClaim));
        when(jwtDecoder.decode("token_valor_nulo")).thenReturn(decodedJWT);

        Map<String, String> claims = decoder.decode("token_valor_nulo");

        assertNotNull(claims);
        assertTrue(claims.isEmpty());
    }
}
