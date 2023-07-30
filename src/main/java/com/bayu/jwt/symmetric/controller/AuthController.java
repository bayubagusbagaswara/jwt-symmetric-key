package com.bayu.jwt.symmetric.controller;

import com.bayu.jwt.symmetric.dto.ApiResponse;
import com.bayu.jwt.symmetric.dto.RegistrationRequest;
import com.bayu.jwt.symmetric.dto.RegistrationResponse;
import com.bayu.jwt.symmetric.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/registration")
    public ResponseEntity<ApiResponse<RegistrationResponse>> registration(@RequestBody RegistrationRequest request) {
        RegistrationResponse registrationResponse = authService.registration(request);
        return ResponseEntity.ok()
                .body(new ApiResponse<>(Boolean.TRUE, "Success", registrationResponse));
    }

}
