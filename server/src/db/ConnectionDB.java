package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionDB {
    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();
        Dotenv dotenv = Dotenv.load();
        String user = dotenv.get("USER");
        String password = dotenv.get("PASSWORD");
        String database = dotenv.get("DATABASE");
        String schema = dotenv.get("SCHEMA");
        props.setProperty("user", user);
        props.setProperty("password", password);
        return DriverManager.getConnection("jdbc:postgresql://localhost/" + database + "?currentSchema=" + schema, props);
    }
}
