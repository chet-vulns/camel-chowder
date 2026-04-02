package com.example.camelchowder.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SystemRoutes extends RouteBuilder {

    @Override
    public void configure() {
        // Vulnerability 2: OS Command Injection (CWE-78)
        from("direct:pingHost")
            .process(exchange -> {
                String host = exchange.getIn().getBody(String.class);
                // VULNERABILITY: Unsanitized input passed to OS command
                Process p = Runtime.getRuntime().exec("ping -c 1 " + host);
                String output = new String(p.getInputStream().readAllBytes());
                exchange.getIn().setBody(output);
            });

        // Vulnerability 11: Header Injection → RCE (CWE-78)
        from("servlet:/api/system-info?httpMethodRestrict=GET")
            // VULNERABILITY: No header filtering — attacker can inject
            // CamelExecCommandExecutable and CamelExecCommandArgs via HTTP params
            .to("exec:uname?args=-a");
    }
}
