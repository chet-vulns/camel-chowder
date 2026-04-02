package com.example.camelchowder.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @GetMapping("/api/files/read")
    public String readFile(@RequestParam String filename) {
        return producerTemplate.requestBody("direct:readFile", filename, String.class);
    }
}
