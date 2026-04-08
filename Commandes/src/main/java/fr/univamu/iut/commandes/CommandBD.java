package fr.univamu.iut.commandes;
import java.sql.*;

public class CommandBD {
    protected Connection dbConnection;

    public CommandBD() throws SQLException, ClassNotFoundException {
        // jdbc:mariadb://mysql-alexexercices.alwaysdata.net/alexexercices_commands
        // User : alexexercices_commands
        // Password : y}3Zh%Qy9]TSzZi
        this.connect("jdbc:mariadb://mysql-alexexercices.alwaysdata.net/alexexercices_commands","alexexercices_commands", "y}3Zh%Qy9]TSzZi");
    }

    /**
     * Create a connection to the database that contains the commands.
     * @param infoConnection A string that contain the information for the connection to the database.
     *                       (e.g. jdbc:mariadb://mysql-[compte].alwaysdata.net/[compte]_library_db)
     * @param user The username used for the connection
     * @param pwd The password used for the connection
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
     * Get all the commands.
     * @return String All the commands
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
    
}
