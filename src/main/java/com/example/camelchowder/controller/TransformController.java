package com.example.camelchowder.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransformController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @PostMapping("/api/transform")
    public String transform(@RequestHeader("X-Template") String template,
                            @RequestBody String data) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("template", template);
        return producerTemplate.requestBodyAndHeaders("direct:transform", data, headers, String.class);
    }
}
