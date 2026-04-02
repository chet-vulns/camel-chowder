package com.example.camelchowder.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @GetMapping(value = "/api/greet", produces = "text/html")
    public String greet(@RequestParam String name) {
        return producerTemplate.requestBody("direct:greet", name, String.class);
    }
}
