package fr.univamu.iut.menus;

import java.util.ArrayList;

public interface MenuRepositoryInterface {

    // close DB connection
    public void close();

    public Menu getMenuById( int id );

    public ArrayList<Menu> getAllMenus();

    public boolean updateMenu(int id, String name, int creatorId, String creatorName, ArrayList<Integer> mealsIds, double totalPrice);

}
