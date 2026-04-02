package com.example.camelchowder.routes;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileRoutes extends RouteBuilder {

    @Override
    public void configure() {
        from("direct:readFile")
            .process(exchange -> {
                String filename = exchange.getIn().getBody(String.class);
                // VULNERABILITY: No path validation — allows ../../etc/passwd
                String content = Files.readString(Paths.get("/var/data/" + filename));
                exchange.getIn().setBody(content);
            });
    }
}
