package com.bayu.jwt.symmetric.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/home")
public class HomeController {

    @GetMapping
    public String home() {
        return "Hello, tokes is valid";
    }

}
