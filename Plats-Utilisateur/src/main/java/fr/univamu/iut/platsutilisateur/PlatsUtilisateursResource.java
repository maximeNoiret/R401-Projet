package fr.univamu.iut.platsutilisateur;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

@Path("/")
@ApplicationScoped
public class PlatsUtilisateursResource {

    private PlatsUtilisateursService service;

    public PlatsUtilisateursResource() {}

    @Inject
    public PlatsUtilisateursResource(PlatsUtilisateursRepositoryInterface repo) {
        this.service = new PlatsUtilisateursService(repo);
    }

    @GET
    @Path("plats")
    @Produces("application/json")
    public String getAllPlats() {
        return service.getAllPlatsJSON();
    }

    @GET
    @Path("plats/{id}")
    @Produces("application/json")
    public String getPlat(@PathParam("id") int id) {
        String result = service.getPlatJSON(id);

        if (result == null) {
            throw new NotFoundException();
        }
        return result;
    }

    @GET
    @Path("utilisateurs")
    @Produces("application/json")
    public String getAllUsers() {
        return service.getAllUsersJSON();
    }

    @GET
    @Path("utilisateurs/{id}")
    @Produces("application/json")
    public String getUser(@PathParam("id") int id) {
        String result = service.getUserJSON(id);

        if (result == null) {
            throw new NotFoundException();
        }
        return result;
    }
}