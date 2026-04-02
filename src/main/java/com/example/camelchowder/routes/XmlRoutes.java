package com.example.camelchowder.routes;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

@Component
public class XmlRoutes extends RouteBuilder {

    @Override
    public void configure() {
        from("direct:parseXml")
            .process(exchange -> {
                String xml = exchange.getIn().getBody(String.class);
                // VULNERABILITY: XML parser with external entities enabled (default)
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(new InputSource(new StringReader(xml)));
                exchange.getIn().setBody(doc.getDocumentElement().getTextContent());
            });
    }
}
