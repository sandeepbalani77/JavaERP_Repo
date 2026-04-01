package com.t2ti.erp.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JwtTokenProviderTest {

    private JwtTokenProvider jwtTokenProvider;

    private static final String SECRET = "test-secret-key-for-jwt-token-generation-minimum-256-bits-long-key-testing";
    private static final long EXPIRATION_MS = 3600000; // 1 hour
    private static final long REFRESH_EXPIRATION_MS = 86400000; // 24 hours

    @BeforeEach
    void setUp() {
        jwtTokenProvider = new JwtTokenProvider(SECRET, EXPIRATION_MS, REFRESH_EXPIRATION_MS);
    }

    @Test
    @DisplayName("Should generate a valid access token")
    void shouldGenerateValidToken() {
        String token = jwtTokenProvider.generateToken("admin", 1L, 1L, true);

        assertThat(token).isNotNull().isNotEmpty();
        assertThat(jwtTokenProvider.validateToken(token)).isTrue();
    }

    @Test
    @DisplayName("Should extract username from token")
    void shouldExtractUsernameFromToken() {
        String token = jwtTokenProvider.generateToken("admin", 1L, 1L, true);

        String username = jwtTokenProvider.getUsernameFromToken(token);

        assertThat(username).isEqualTo("admin");
    }

    @Test
    @DisplayName("Should extract userId from token")
    void shouldExtractUserIdFromToken() {
        String token = jwtTokenProvider.generateToken("admin", 42L, 1L, true);

        Long userId = jwtTokenProvider.getUserIdFromToken(token);

        assertThat(userId).isEqualTo(42L);
    }

    @Test
    @DisplayName("Should extract empresaId from token")
    void shouldExtractEmpresaIdFromToken() {
        String token = jwtTokenProvider.generateToken("admin", 1L, 5L, true);

        Long empresaId = jwtTokenProvider.getEmpresaIdFromToken(token);

        assertThat(empresaId).isEqualTo(5L);
    }

    @Test
    @DisplayName("Should extract admin flag from token")
    void shouldExtractAdminFlagFromToken() {
        String adminToken = jwtTokenProvider.generateToken("admin", 1L, 1L, true);
        String userToken = jwtTokenProvider.generateToken("user", 2L, 1L, false);

        assertThat(jwtTokenProvider.isAdmin(adminToken)).isTrue();
        assertThat(jwtTokenProvider.isAdmin(userToken)).isFalse();
    }

    @Test
    @DisplayName("Should generate a valid refresh token")
    void shouldGenerateValidRefreshToken() {
        String refreshToken = jwtTokenProvider.generateRefreshToken("admin", 1L);

        assertThat(refreshToken).isNotNull().isNotEmpty();
        assertThat(jwtTokenProvider.validateToken(refreshToken)).isTrue();
        assertThat(jwtTokenProvider.isRefreshToken(refreshToken)).isTrue();
    }

    @Test
    @DisplayName("Access token should not be identified as refresh token")
    void accessTokenShouldNotBeRefreshToken() {
        String token = jwtTokenProvider.generateToken("admin", 1L, 1L, true);

        assertThat(jwtTokenProvider.isRefreshToken(token)).isFalse();
    }

    @Test
    @DisplayName("Should reject invalid token")
    void shouldRejectInvalidToken() {
        assertThat(jwtTokenProvider.validateToken("invalid.token.here")).isFalse();
    }

    @Test
    @DisplayName("Should reject null token")
    void shouldRejectNullToken() {
        assertThat(jwtTokenProvider.validateToken(null)).isFalse();
    }

    @Test
    @DisplayName("Should reject empty token")
    void shouldRejectEmptyToken() {
        assertThat(jwtTokenProvider.validateToken("")).isFalse();
    }

    @Test
    @DisplayName("Should reject expired token")
    void shouldRejectExpiredToken() {
        // Create a provider with 0ms expiration (token is immediately expired)
        JwtTokenProvider shortLivedProvider = new JwtTokenProvider(SECRET, 0, 0);
        String token = shortLivedProvider.generateToken("admin", 1L, 1L, true);

        assertThat(shortLivedProvider.validateToken(token)).isFalse();
    }

    @Test
    @DisplayName("Should handle null empresaId in token")
    void shouldHandleNullEmpresaIdInToken() {
        String token = jwtTokenProvider.generateToken("admin", 1L, null, true);

        assertThat(jwtTokenProvider.validateToken(token)).isTrue();
        assertThat(jwtTokenProvider.getEmpresaIdFromToken(token)).isEqualTo(0L);
    }

    @Test
    @DisplayName("Should return correct expiration time")
    void shouldReturnCorrectExpirationTime() {
        assertThat(jwtTokenProvider.getExpirationMs()).isEqualTo(EXPIRATION_MS);
    }
}
