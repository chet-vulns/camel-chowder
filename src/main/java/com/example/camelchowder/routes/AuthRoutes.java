package com.example.camelchowder.routes;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthRoutes extends RouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger("AuthService");

    @Override
    public void configure() {
        from("direct:login")
            .process(exchange -> {
                String credentials = exchange.getIn().getBody(String.class);
                String username = credentials.split(":")[0];
                // VULNERABILITY: User input logged without sanitization
                logger.info("Login attempt for user: " + username);
                exchange.getIn().setBody("Login processed for: " + username);
            });
    }
}
