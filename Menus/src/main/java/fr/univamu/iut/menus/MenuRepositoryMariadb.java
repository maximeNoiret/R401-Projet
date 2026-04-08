package fr.univamu.iut.menus;

import jakarta.enterprise.context.ApplicationScoped;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

public class MenuRepositoryMariadb implements MenuRepositoryInterface, Closeable {
    protected Connection dbConnection;

    public MenuRepositoryMariadb(String infoConnection, String user, String pwd ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection( infoConnection, user, pwd ) ;
    }

    @Override
    public void close() {
        try{
            dbConnection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Menu getMenuById(int id) {
        Menu selectedmenu = null;

        String query = "SELECT * FROM menu WHERE id= ?";

        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                selectedmenu = new Menu(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("creator_id"),
                        rs.getDate("creation_date").toLocalDate(),
                        rs.getDate("last_update_date").toLocalDate(),
                        rs.getString("creator_id"),
                        rs.getDouble("total_price")
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return selectedmenu;
    }

    @Override
    public ArrayList<Menu> getAllMenus() {
        return null;  // TODO: get all menus from DB
    }

    @Override
    public boolean updateMenu(int id, String name, int creatorId, String creatorName, ArrayList<Integer> mealsIds, double totalPrice) {
        return false;
    }
}
