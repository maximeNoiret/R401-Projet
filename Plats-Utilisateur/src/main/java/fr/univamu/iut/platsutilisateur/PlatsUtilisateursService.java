package fr.univamu.iut.platsutilisateur;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

/**
 * Classe qui transforme les données Java (Plat, User) en texte JSON.
 */
public class PlatsUtilisateursService {

    protected PlatsUtilisateursRepositoryInterface repo;

    /**
     * Constructeur : on lui donne la connexion à la base de données quand on le crée.
     */
    public PlatsUtilisateursService(PlatsUtilisateursRepositoryInterface repo) {
        this.repo = repo;
    }

    /**
     * Récupère tous les plats et les transforme en JSON.
     */
    public String getAllPlatsJSON() {
        ArrayList<Plat> allPlats = repo.getAllPlats();
        String result = null;

        // Création du JSON
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allPlats);
        } catch (Exception e) {
            System.err.println("Erreur JSON (Tous les plats) : " + e.getMessage());
        }
        return result;
    }

    /**
     * Récupère un seul plat et le transforme en JSON.
     */
    public String getPlatJSON(int id) {
        Plat monPlat = repo.getPlat(id);
        String result = null;

        if (monPlat != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(monPlat);
            } catch (Exception e) {
                System.err.println("Erreur JSON (Un plat) : " + e.getMessage());
            }
        }
        return result;
    }

    /**
     * Récupère tous les utilisateurs et les transforme en JSON.
     */
    public String getAllUsersJSON() {
        ArrayList<User> allUsers = repo.getAllUsers();
        String result = null;

        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allUsers);
        } catch (Exception e) {
            System.err.println("Erreur JSON (Tous les users) : " + e.getMessage());
        }
        return result;
    }

    /**
     * Récupère un seul utilisateur et le transforme en JSON.
     */
    public String getUserJSON(int id) {
        User monUser = repo.getUser(id);
        String result = null;

        if (monUser != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(monUser);
            } catch (Exception e) {
                System.err.println("Erreur JSON (Un user) : " + e.getMessage());
            }
        }
        return result;
    }
}