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
}