package com.example.camelchowder.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XmlController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @PostMapping(value = "/api/xml/parse", consumes = "application/xml")
    public String parseXml(@RequestBody String xmlData) {
        return producerTemplate.requestBody("direct:parseXml", xmlData, String.class);
    }
}
