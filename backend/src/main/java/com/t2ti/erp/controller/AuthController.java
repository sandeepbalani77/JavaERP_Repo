package com.t2ti.erp.controller;

import com.t2ti.erp.dto.*;
import com.t2ti.erp.security.UserPrincipal;
import com.t2ti.erp.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication and authorization endpoints")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate user and return JWT token")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh JWT token")
    public ResponseEntity<LoginResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        LoginResponse response = authService.refreshToken(request.getRefreshToken());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    @Operation(summary = "Get current authenticated user info")
    public ResponseEntity<UsuarioDTO> getCurrentUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        UsuarioDTO usuario = authService.getCurrentUser(userPrincipal.getUsername());
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/permissions")
    @Operation(summary = "Get current user's function and button permissions")
    public ResponseEntity<List<PapelDTO>> getUserPermissions(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<PapelDTO> permissions = authService.getUserPermissions(userPrincipal.getId());
        return ResponseEntity.ok(permissions);
    }

    @PostMapping("/logout")
    @Operation(summary = "Logout user (client should discard token)")
    public ResponseEntity<Map<String, String>> logout() {
        return ResponseEntity.ok(Map.of("message", "Logout realizado com sucesso"));
    }
}
