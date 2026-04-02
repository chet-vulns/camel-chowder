package com.example.camelchowder.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @GetMapping("/api/users/search")
    public String searchUsers(@RequestParam String name) {
        return producerTemplate.requestBody("direct:searchUsers", name, String.class);
    }
}
