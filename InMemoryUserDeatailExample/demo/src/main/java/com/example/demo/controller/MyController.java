package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint.";
    }

    @GetMapping("/user")
    public String userEndpoint() {
        return "This is for authenticated users.";
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "This is for administrators only.";
    }
}
