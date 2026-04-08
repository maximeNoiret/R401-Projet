package fr.univamu.iut.menus;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public class MenuService {

    protected MenuRepositoryInterface menuRepo;

    public MenuService(MenuRepositoryInterface menuRepo) {
        this.menuRepo = menuRepo;
    }

    public String getMenuJSONFromId( int id ){
        String result = null;
        Menu menu = menuRepo.getMenuById(id);

        // si le livre a été trouvé
        if( menu != null ) {

            // création du json et conversion du livre
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(menu);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }
}
