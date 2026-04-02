package com.example.camelchowder.routes;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class LdapRoutes extends RouteBuilder {

    @Override
    public void configure() {
        from("direct:ldapSearch")
            .process(exchange -> {
                String username = exchange.getIn().getBody(String.class);
                Hashtable<String, String> env = new Hashtable<>();
                env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
                env.put(Context.PROVIDER_URL, "ldap://localhost:389");
                DirContext ctx = new InitialDirContext(env);
                // VULNERABILITY: Unsanitized user input in LDAP filter
                String filter = "(uid=" + username + ")";
                NamingEnumeration<?> results = ctx.search("dc=example,dc=com", filter, new SearchControls());
                exchange.getIn().setBody(results.hasMore() ? results.next().toString() : "Not found");
            });
    }
}
