package com.bayu.jwt.symmetric.service;

import com.bayu.jwt.symmetric.dto.RegistrationRequest;
import com.bayu.jwt.symmetric.dto.RegistrationResponse;

public interface AuthService {

    RegistrationResponse registration(RegistrationRequest registrationRequest);

}
