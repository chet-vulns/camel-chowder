package com.example.camelchowder.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LdapController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @GetMapping("/api/ldap/search")
    public String ldapSearch(@RequestParam String username) {
        return producerTemplate.requestBody("direct:ldapSearch", username, String.class);
    }
}
