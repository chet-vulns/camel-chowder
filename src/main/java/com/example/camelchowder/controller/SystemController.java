package com.example.camelchowder.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @GetMapping("/api/system/ping")
    public String pingHost(@RequestParam String host) {
        return producerTemplate.requestBody("direct:pingHost", host, String.class);
    }
}
