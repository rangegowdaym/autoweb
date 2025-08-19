package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBReader {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public Connection connect(String dbUrl, String driverClass, String user, String password) {
        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(dbUrl, user, password);
        } catch (Exception e) {
            throw new RuntimeException("Database connection failed: " + e.getMessage(), e);
        }
        return connection;
    }

    public Statement createStatement() {
        try {
            if (connection == null || connection.isClosed()) {
                throw new IllegalStateException("Connection is not available.");
            }
            return connection.createStatement();
        } catch (Exception e) {
            throw new RuntimeException("Statement creation failed: " + e.getMessage(), e);
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            if (statement == null) {
                statement = createStatement();
            }
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            throw new RuntimeException("Query execution failed: [" + query + "] - " + e.getMessage(), e);
        }
        return resultSet;
    }

    public void closeConnection() {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (Exception e) {
            throw new RuntimeException("Failed to close resources: " + e.getMessage(), e);
        }
    }
}