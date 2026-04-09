package fr.univamu.iut.commandes;
import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * Class that manage connection and SQL query to the database used in the api "Commandes".
 */
public class CommandBD {
    /**
     * Connection object that is used to interact to the database.
     */
    protected Connection dbConnection;

    /**
     * Constructor of the class CommandDB, the connection to the DB is hardcoded
     * in the class, meaning that there is no natural way to make a connection to another class.
     * @throws SQLException Thrown if there is an error to the access of a database.
     * @throws ClassNotFoundException Thrown if the class is not found.
     */
    public CommandBD() throws SQLException, ClassNotFoundException {
        this.connect("jdbc:mariadb://mysql-alexexercices.alwaysdata.net/alexexercices_commands","alexexercices_commands", "y}3Zh%Qy9]TSzZi");
    }

    /**
     * Create a connection to the database that contains the commands.
     * @param infoConnection A string that contain the information for the connection to the database.
     *                       (e.g. jdbc:mariadb://mysql-[compte].alwaysdata.net/[compte]_library_db)
     * @param user The username used for the connection.
     * @param pwd The password used for the connection.
     */
    public void connect(String infoConnection, String user, String pwd ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection( infoConnection, user, pwd );
    }

    /**
     * Close the connection to the database.
     */
    public void closeConnection() {
        try{
            dbConnection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return dbConnection;
    }

    /**
     * Get the id of the created command. Since the id of a command is an auto
     * increment, the newest command has automatically the highest id.
     * @return The highest command id in the database.
     */
    public Integer getMaxId(){
        String getIdQuery = "SELECT MAX id FROM commands";
        try (PreparedStatement ps = dbConnection.prepareStatement(getIdQuery)){
            ResultSet result = ps.executeQuery();
            return result.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get a specific command.
     * @param id The ID of the wanted command.
     * @return The string that contain all the data of the command.
     */
    public String getCommand(int id){
        String query = "SELECT * FROM commands WHERE id = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)){
            return ps.executeQuery().getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the address of delivery of a specific command.
     * @param id The ID the specified command.
     * @return The address of delivery.
     */
    public String getAdrsDelivery(int id){
        String query = "SELECT deliveryAddress FROM adresses WHERE id = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)){
            ps.setInt(1, id);
            return ps.executeQuery().getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the deadline (represent a date) of a specific command.
     * @param id The ID od the specified command.
     * @return The deadline of the command.
     */
    public String getDeadline(int id){
        String query = "SELECT deadline FROM commands WHERE id = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)){
            ps.setInt(1, id);
            return ps.executeQuery().getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get all the commands.
     * @return All the commands.
     */
    public String getAllCommands(){
        String query = "select * from commands";

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // execute query
            ResultSet result = ps.executeQuery();
            return result.toString();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get all the command made a specific subscriber.
     * @param subscriberId The ID of the specific subscriber.
     * @return All the command made by the subscriber.
     */
    public String getAllCommandsBySubscriber(int subscriberId){
        String query = "SELECT * FROM commands WHERE subscriberId = ?";
        try(PreparedStatement ps = dbConnection.prepareStatement(query)){
            ps.setInt(1, subscriberId);
            return ps.executeQuery().toString();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Get a command specified by his id.
     * @param id The id of the command.
     * @return The specified command.
     */
    public String getCommand(String id){
        String query = "select * from commands where id = ?";
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1, Integer.parseInt(id));
            // execute query
            ResultSet result = ps.executeQuery();
            return result.toString();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Create a new Commands.
     * @param subscriberId The ID of the subscriber that made the command.
     * @param adrsDelivery The address of delivery of the command.
     * @param deadline The deadline for the delivery.
     * @param menus A map where the key is the id of a menu, and the value the quantity of the key menu.
     */
    public void createCommand(int subscriberId, String adrsDelivery, String deadline, Map<Integer,Integer> menus){
        // create a command
        String query = "INSERT INTO Command(deliveryAddress, deadline, subscriberId) VALUES (?, ?, ?)";
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, adrsDelivery);
            ps.setString(2, deadline);
            ps.setInt(3, subscriberId);
            // execute query
            ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // create the link between the different menus and the command where they are present
        // TODO : look if there is a better way to do this
        for(Integer key : menus.keySet()){
            String queryContainMenu = "INSERT INTO contains_menu(command_id, menu_id, quantity) VALUES (?, ?, ?)";
            try ( PreparedStatement ps = dbConnection.prepareStatement(queryContainMenu) ){
                ps.setInt(1, getMaxId());
                ps.setInt(2, key);
                ps.setInt(3, menus.get(key));
                // execute query
                ps.executeQuery();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };
    }

    /**
     * Update the address and the deadline of a command.
     * @param id The ID of the command to update
     * @param adrsDelivery The new address of delivery.
     * @param deadline The new deadline (represent a date).
     */
    public void updateCommand(int id, String adrsDelivery, String deadline){
        String query = "UPDATE commands SET deliveryAddress = ?, deadline = ? WHERE id = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)){
            ps.setString(1, adrsDelivery);
            ps.setString(2, deadline);
            ps.setInt(3, id);

            ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Delete a command.
     * @param id The id of the command to be deleted.
     */
    public void deleteCommand(int id){
        String query = "DELETE FROM commands WHERE id = ?";
        try(PreparedStatement ps = dbConnection.prepareStatement(query)){
            ps.setInt(1, id);
            ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
