package com.example.camelchowder.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @GetMapping("/api/redirect")
    public ResponseEntity<Void> redirect(@RequestParam String target) {
        String resolvedUrl = producerTemplate.requestBody("direct:resolveRedirect", target, String.class);
        // VULNERABILITY: User-controlled redirect target, no validation
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", resolvedUrl).build();
    }
}
