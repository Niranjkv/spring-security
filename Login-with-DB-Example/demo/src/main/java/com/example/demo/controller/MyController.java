package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyController {


    @GetMapping("/home")    
    public String home(){
        return "Welcome to HomePage";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "This is Admin";
    }

    @GetMapping("/user")
    public String userPage() {
        return "This is user";
    }
    
    
}
