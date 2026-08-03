
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database {
   public Connection connect() throws ClassNotFoundException, SQLException{
    Class.forName("org.postgresql.Driver");
    Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/BikeServiceCenterManagementSystem","postgres","kannan");
    return c;
    } 
}
