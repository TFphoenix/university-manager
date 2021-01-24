package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionConfiguration {

    // Database information
    final private static String dbType = "jdbc:postgresql://";
    final private static String dbURL = "localhost:";
    final private static String dbPort = "5432/";
    final private static String dbName = "mip_unitbvapp";
    final private static String dbUser = "postgres";
    final private static String dbPass = "1q2w3e";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // JDBC driver
            Class.forName("org.postgresql.Driver");
            // Establish connection
            connection = DriverManager.getConnection(dbType + dbURL + dbPort + dbName, dbUser, dbPass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
