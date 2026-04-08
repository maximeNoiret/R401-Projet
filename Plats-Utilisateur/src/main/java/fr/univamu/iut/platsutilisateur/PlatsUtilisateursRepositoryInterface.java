perpackage fr.univamu.iut.platsutilisateur;

import java.util.ArrayList;

public interface PlatsUtilisateursRepositoryInterface {

    void close();

    ArrayList<Plat> getAllPlats();

    Plat getPlat(int id);

    ArrayList<User> getAllUsers();

    User getUser(int id);
}