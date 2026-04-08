package fr.univamu.iut.platsutilisateur;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@ApplicationScoped
public class PlatsUtilisateursApplication extends Application {

    /**
     * Méthode appelée automatiquement pour créer la connexion à AlwaysData.
     * Le @Produces permet d'injecter cette connexion partout où on en aura besoin.
     */
    @Produces
    private PlatsUtilisateursRepositoryInterface openDbConnection() {
        PlatsUtilisateursRepositoryMariadb db = null;

        try {
            String url = "jdbc:mariadb://mysql-mada-r401-project.alwaysdata.net/mada-r401-project_db_plats_utilisateurs";
            String user = "mada-r401-project_utilisateur";
            String password = "LeMDPcestoufmaisvoilajailaflmedentrouver1";

            db = new PlatsUtilisateursRepositoryMariadb(url, user, password);
        }
        catch (Exception e) {
            System.err.println("Erreur de connexion à la BD : " + e.getMessage());
        }
        return db;
    }

    /**
     * Méthode appelée automatiquement quand on arrête GlassFish pour fermer la connexion.
     */
    private void closeDbConnection(@Disposes PlatsUtilisateursRepositoryInterface repo) {
        if (repo != null) {
            repo.close();
        }
    }
}