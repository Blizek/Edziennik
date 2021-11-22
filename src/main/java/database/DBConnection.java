package database;

import java.sql.*;

public class DBConnection {
    private static DBConnection dbConnection;
    private static Connection connection;
    final String DB_URL = "jdbc:mysql://localhost:3306/dziennik";
    final String USER = "root";
    final String PASSWORD = "rootzxcvROOTZXCV";

    private DBConnection() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    public static Connection getConnection() throws SQLException
    {
        if (dbConnection == null) dbConnection = new DBConnection();
        return connection;
    }
}
