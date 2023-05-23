package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDB {
    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "s367143");
        props.setProperty("password", "3UAg6OGym7gQFGEp");
        return DriverManager.getConnection("jdbc:postgresql://localhost/studs", props);
    }
}
