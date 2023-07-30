package com.bayu.jwt.symmetric.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/home")
public class HomeController {

    @GetMapping
    public String home(Authentication authentication) {
        return "Hello ," + authentication.getName() + "token is valid";
    }

}
