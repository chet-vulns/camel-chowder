package com.example.camelchowder.routes;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DataImportRoutes extends RouteBuilder {

    @Override
    public void configure() {
        from("direct:importData")
            .process(exchange -> {
                byte[] data = exchange.getIn().getBody(byte[].class);
                // VULNERABILITY: Deserializing untrusted data
                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
                Object obj = ois.readObject();
                exchange.getIn().setBody(obj.toString());
            });
    }
}
