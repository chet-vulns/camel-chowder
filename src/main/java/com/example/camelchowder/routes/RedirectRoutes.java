package com.example.camelchowder.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RedirectRoutes extends RouteBuilder {

    @Override
    public void configure() {
        from("direct:resolveRedirect")
            .process(exchange -> {
                String target = exchange.getIn().getBody(String.class);
                // VULNERABILITY: User-controlled redirect target passed through without validation
                exchange.getIn().setBody(target);
            });
    }
}
