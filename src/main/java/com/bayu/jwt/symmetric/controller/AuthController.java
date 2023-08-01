package com.bayu.jwt.symmetric.controller;

import com.bayu.jwt.symmetric.dto.*;
import com.bayu.jwt.symmetric.service.AuthService;
import com.bayu.jwt.symmetric.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final TokenService tokenService;

    @PostMapping(path = "/registration")
    public ResponseEntity<ApiResponse<RegistrationResponse>> registration(@RequestBody RegistrationRequest request) {
        RegistrationResponse registrationResponse = authService.registration(request);
        return ResponseEntity.ok()
                .body(new ApiResponse<>(Boolean.TRUE, "Success", registrationResponse));
    }

    @PostMapping(path = "/signin")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> signIn(@RequestBody LoginRequest loginRequest) {
        AuthenticationResponse authenticationResponse = authService.signIn(loginRequest);
        return ResponseEntity.ok()
                .body(new ApiResponse<>(Boolean.TRUE, "Success", authenticationResponse));
    }

    @PostMapping(path = "/token")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> token(@RequestBody LoginRequest loginRequest) {
        AuthenticationResponse authenticationResponse = authService.signIn(loginRequest);
        return ResponseEntity.ok()
                .body(new ApiResponse<>(Boolean.TRUE, "Success", authenticationResponse));
    }
    @GetMapping(path = "/token/basic-auth")
    public String token(Authentication authentication) {
        return tokenService.generateToken(authentication);
    }

    @GetMapping("/authorities")
    public Map<String,Object> getPrincipalInfo(JwtAuthenticationToken principal) {

        Collection<String> authorities = principal.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        Map<String,Object> info = new HashMap<>();
        info.put("name", principal.getName());
        info.put("authorities", authorities);
        info.put("tokenAttributes", principal.getTokenAttributes());

        return info;
    }
}
