package com.example.camelchowder.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ProxyRoutes extends RouteBuilder {

    @Override
    public void configure() {
        from("direct:fetchUrl")
            .process(exchange -> {
                String url = exchange.getIn().getBody(String.class);
                // VULNERABILITY: User-controlled URL passed to HTTP component
                exchange.getIn().setHeader(Exchange.HTTP_URI, url);
                exchange.getIn().setBody(null);
            })
            .toD("http://placeholder?bridgeEndpoint=true&throwExceptionOnFailure=false")
            .process(exchange -> {
                String response = exchange.getIn().getBody(String.class);
                exchange.getIn().setBody(response);
            });
    }
}
