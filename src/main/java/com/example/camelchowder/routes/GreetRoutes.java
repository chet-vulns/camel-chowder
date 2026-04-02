package com.example.camelchowder.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class GreetRoutes extends RouteBuilder {

    @Override
    public void configure() {
        from("direct:greet")
            .process(exchange -> {
                String name = exchange.getIn().getBody(String.class);
                // VULNERABILITY: User input directly embedded in HTML without escaping
                String html = "<html><body><h1>Welcome, " + name + "!</h1></body></html>";
                exchange.getIn().setBody(html);
            });
    }
}
