package com.bayu.jwt.symmetric.service.impl;

import com.bayu.jwt.symmetric.dto.AuthenticationResponse;
import com.bayu.jwt.symmetric.dto.LoginRequest;
import com.bayu.jwt.symmetric.dto.RegistrationRequest;
import com.bayu.jwt.symmetric.dto.RegistrationResponse;
import com.bayu.jwt.symmetric.model.Role;
import com.bayu.jwt.symmetric.model.User;
import com.bayu.jwt.symmetric.repository.RoleRepository;
import com.bayu.jwt.symmetric.repository.UserRepository;
import com.bayu.jwt.symmetric.service.AuthService;
import com.bayu.jwt.symmetric.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @Override
    public RegistrationResponse registration(RegistrationRequest registrationRequest) {
        Role roleRead = roleRepository.findFirstByAuthority("READ")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role READ not found"));

        Role roleUser = roleRepository.findFirstByAuthority("ROLE_USER")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role USER not found"));

        Set<Role> authorities = new HashSet<>();
        authorities.add(roleRead);
        authorities.add(roleUser);

        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setAuthorities(authorities);

        userRepository.save(user);

        return RegistrationResponse.builder()
                .success(Boolean.TRUE)
                .message("Registration successfully with id : " + user.getId())
                .build();
    }

    @Override
    public AuthenticationResponse signIn(LoginRequest request) {
        try {
            log.info("Start sign in with username : {}", request.getUsername());
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword()
            ));
            log.info("Authenticate : {}", authenticate.getName());
            String token = tokenService.generateToken(authenticate);
            log.info("Token : {}", token);
            return AuthenticationResponse.builder()
                    .token(token)
                    .build();
        } catch (AuthenticationException e) {
            return new AuthenticationResponse(e.getMessage());
        }
    }

}
