package fr.univamu.iut.platsutilisateur;

import fr.univamu.iut.platsutilisateur.PlatsUtilisateursRepositoryInterface;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe qui se connecte concrètement à AlwaysData avec JDBC.
 */
public class PlatsUtilisateursRepositoryMariadb implements PlatsUtilisateursRepositoryInterface, Closeable {

    protected Connection dbConnection;

    /**
     * Constructeur : C'est ici qu'on se connecte à la BD
     */
    public PlatsUtilisateursRepositoryMariadb(String infoConnection, String user, String pwd) throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, user, pwd);
    }

    @Override
    public void close() {
        try {
            if (dbConnection != null) {
                dbConnection.close();
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la fermeture : " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Plat> getAllPlats() {
        ArrayList<Plat> listePlats = new ArrayList<>();
        String query = "SELECT * FROM Plat";

        try (java.sql.PreparedStatement ps = dbConnection.prepareStatement(query)) {
            java.sql.ResultSet result = ps.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                String nom = result.getString("nom");
                String description = result.getString("description");
                double prix = result.getDouble("prix");

                Plat platCourant = new Plat(id, nom, description, prix);
                listePlats.add(platCourant);
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL getAllPlats : " + e.getMessage());
        }
        return listePlats;
    }

    @Override
    public Plat getPlat(int id) {
        Plat platTrouve = null;
        String query = "SELECT * FROM Plat WHERE id = ?";

        try (java.sql.PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            java.sql.ResultSet result = ps.executeQuery();

            if (result.next()) {
                String nom = result.getString("nom");
                String description = result.getString("description");
                double prix = result.getDouble("prix");

                platTrouve = new Plat(id, nom, description, prix);
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL getPlat : " + e.getMessage());
        }
        return platTrouve;
    }
}