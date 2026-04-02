package com.example.camelchowder.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @GetMapping("/api/proxy/fetch")
    public String fetchUrl(@RequestParam String url) {
        return producerTemplate.requestBody("direct:fetchUrl", url, String.class);
    }
}
