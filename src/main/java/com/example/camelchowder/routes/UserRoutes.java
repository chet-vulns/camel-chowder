package com.example.camelchowder.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserRoutes extends RouteBuilder {

    @Override
    public void configure() {
        from("direct:searchUsers")
            .process(exchange -> {
                String name = exchange.getIn().getBody(String.class);
                // VULNERABILITY: String concatenation in SQL query
                String query = "SELECT * FROM users WHERE name = '" + name + "'";
                exchange.getIn().setBody(query);
            })
            .to("jdbc:dataSource");
    }
}
