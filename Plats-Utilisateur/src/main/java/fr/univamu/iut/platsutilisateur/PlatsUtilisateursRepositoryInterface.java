package fr.univamu.iut.platsutilisateur;

import java.util.ArrayList;

/**
 * Interface qui liste toutes les actions possibles sur la base de données.
 */
public interface PlatsUtilisateursRepositoryInterface {

    // Méthode obligatoire pour fermer la connexion proprement
    void close();

    // On ajoutera les méthodes pour récupérer les plats et utilisateurs ici après,
    // par exemple : ArrayList<Plat> getAllPlats();
}