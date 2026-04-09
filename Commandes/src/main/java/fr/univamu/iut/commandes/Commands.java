package fr.univamu.iut.commandes;

import jakarta.ws.rs.*;

import java.sql.SQLException;
import java.util.Map;

/**
 * Manage the commands that are stored in the api.
 */
@Path("/commandes")
public class Commands {

    /**
     * Class Attribute that store a object of class CommandDB.
     */
    protected CommandBD commandBD;

    // url : http://localhost:8082/commandes/api

    /**
     * Constructor of the class Commands. Create an object of CommandDB class,
     * that is stored in the class attribute "commandDB".
     * NOTE : The exception come from the "CommandDB" class.
     * @throws SQLException Thrown if there is an error of connection or other to the database.
     * @throws ClassNotFoundException Thrown if the class is not found?
     */
    public Commands() throws SQLException, ClassNotFoundException {
        commandBD = new CommandBD();
    }

    /**
     * Return all the commands made by a specific subscriber.
     * @param subscriberId The id of the subscriber that ordered the commands.
     * @return All the command made by the subscriber.
     */
    @Path("{subscriberId}")
    @GET
    public String getAllCommandes(int subscriberId){
        //TODO : implement it, more info in openapi-commandes.yaml.yaml
        return commandBD.getAllCommandsBySubscriber(subscriberId);
    }

    /**
     *
     * @param subscriberId The id of the subscriber that made the command.
     * @param adrsDelivery The address of delivery.
     * @param deadline The deadline of the command.
     * @param menus A map where the key is the id of a menu, and the value the quantity of the key menu.
     */
    @POST
    public void CreateCommande(int subscriberId, String adrsDelivery, String deadline, Map<Integer,Integer> menus){
        commandBD.createCommand(subscriberId, adrsDelivery, deadline, menus);
    }

    /**
     * Get a specific command by using his id.
     * @param id The id of the command.
     * @return The command.
     */
    @Path("{id}")
    @GET
    public String getCommandeById(int id){
        //TODO : implement it
        return commandBD.getCommand(id);
    }

    /**
     * Update the address of delivery and the deadline of a command
     * @param id The id of the command to update.
     * @param adrsDelivery The new address of delivery.
     * @param deadline The new deadline.
     */
    @PUT
    public void updateCommande(int id, String adrsDelivery, String deadline){
        commandBD.updateCommand(id, adrsDelivery, deadline);
    }

    /**
     * Delete the given command.
     * @param id The id of the command to be deleted.
     */
    @DELETE
    public void deleteCommande(int id){
        commandBD.deleteCommand(id);
    }


}
