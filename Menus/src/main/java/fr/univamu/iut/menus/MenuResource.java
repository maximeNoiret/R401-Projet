package fr.univamu.iut.menus;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/menus")
@ApplicationScoped
public class MenuResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
}