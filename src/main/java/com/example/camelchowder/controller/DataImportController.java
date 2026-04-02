package com.example.camelchowder.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataImportController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @PostMapping(value = "/api/data/import", consumes = "application/octet-stream")
    public String importData(@RequestBody byte[] data) {
        return producerTemplate.requestBody("direct:importData", data, String.class);
    }
}
