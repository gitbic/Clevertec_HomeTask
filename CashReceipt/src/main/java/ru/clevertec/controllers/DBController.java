package ru.clevertec.controllers;

import ru.clevertec.constants.JdbcConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBController {

    private final String dbName;
    private final String url;
    private final String user;
    private final String password;

    public DBController(String connectionPropertiesFile) {
        ResourceBundle rb = ResourceBundle.getBundle(connectionPropertiesFile);
        dbName = rb.getString("dbName");
        url = rb.getString("url") + dbName;
        user = rb.getString("user");
        password = rb.getString("password");
    }

    public Connection getConnection() throws SQLException {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println(String.format(JdbcConstants.FMT_DATABASE_CONNECTION_FAILED, dbName));
            e.printStackTrace();
        }

        return connection;

    }

    private void checkDriverRegistered() {
        boolean driverFound = true;

        try {
            Class.forName(JdbcConstants.POSTGRESQL_DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            driverFound = false;
        }

        System.out.println(driverFound
                ? JdbcConstants.POSTGRESQL_DRIVER_FOUND
                : JdbcConstants.POSTGRESQL_DRIVER_NOT_FOUND);

    }
}
