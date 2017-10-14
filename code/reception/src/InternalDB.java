import java.sql.*;


public class InternalDB {
    boolean offline = false;
    private Connection connection;


    InternalDB(){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e) {
            this.offline = true;
            System.out.println("[FATAL] could not load db driver");
            e.printStackTrace();
        }

        try{
            connection = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/~bohl", "bohl", "bohl");
        }
        catch(SQLException e) {
            offline = true;
            System.out.println("[FATAL] could not connect to db");
            e.printStackTrace();
        }
    }
}
