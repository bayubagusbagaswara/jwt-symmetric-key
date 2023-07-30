package com.bayu.jwt.symmetric.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(path = "/")
    public String home() {
        return "Hello, tokes is valid";
    }

}
