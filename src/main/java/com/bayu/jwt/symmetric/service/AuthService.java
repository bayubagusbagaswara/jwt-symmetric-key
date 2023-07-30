package com.bayu.jwt.symmetric.service;

import com.bayu.jwt.symmetric.dto.AuthenticationResponse;
import com.bayu.jwt.symmetric.dto.LoginRequest;
import com.bayu.jwt.symmetric.dto.RegistrationRequest;
import com.bayu.jwt.symmetric.dto.RegistrationResponse;

public interface AuthService {

    RegistrationResponse registration(RegistrationRequest registrationRequest);

    AuthenticationResponse signIn(LoginRequest request);
}
