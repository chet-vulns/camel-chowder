package com.example.camelchowder.routes;

import org.apache.camel.Expression;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TransformRoutes extends RouteBuilder {

    @Override
    public void configure() {
        from("direct:transform")
            .process(exchange -> {
                String template = exchange.getIn().getHeader("template", String.class);
                // VULNERABILITY: User-controlled string evaluated as Simple expression
                Expression expr = exchange.getContext().resolveLanguage("simple").createExpression(template);
                String result = expr.evaluate(exchange, String.class);
                exchange.getIn().setBody(result);
            });
    }
}
