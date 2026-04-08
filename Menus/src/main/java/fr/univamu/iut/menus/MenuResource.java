package fr.univamu.iut.menus;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/menus")
@ApplicationScoped
public class MenuResource {

    private MenuService menuService;

    public MenuResource() {}

    public @Inject MenuResource(MenuRepositoryInterface menuRepo ){
        this.menuService = new MenuService(menuRepo) ;
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getMenu( @PathParam("id") int id){

        String result = menuService.getMenuJSONFromId(id);

        // si le livre n'a pas été trouvé
        if( result == null )
            throw new NotFoundException();

        return result;
    }
}