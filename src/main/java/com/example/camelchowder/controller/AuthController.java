package com.example.camelchowder.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @PostMapping("/api/auth/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        String credentials = username + ":" + password;
        return producerTemplate.requestBody("direct:login", credentials, String.class);
    }
}
