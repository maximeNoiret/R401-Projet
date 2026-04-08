package fr.univamu.iut.menus;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ApplicationPath;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@ApplicationScoped
public class MenuApplication extends Application {
    @Produces
    private MenuRepositoryInterface openDbConnection(){
        try{
            return new MenuRepositoryMariadb("jdbc:mariadb://localhost:3306/MealDB", "book-td_library", "123");
        }
        catch (Exception e){
            throw new RuntimeException(e); // actually show the error
        }
    }
}