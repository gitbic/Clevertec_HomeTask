package ru.clevertec.controllers;

import ru.clevertec.constants.JdbcConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBController {
    private final String dbName = "cash_receipt";
    private final String url = "jdbc:postgresql://localhost:5432/" + dbName;
    private final String user = "postgres";
    private final String password = "";

    public Connection getConnection() throws SQLException {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println(String.format(JdbcConstants.FMT_DATABASE_CONNECTION_FAILED, dbName));
            e.printStackTrace();
        }

//        System.out.println(String.format(JdbcConstants.FMT_DATABASE_CONNECTION_ESTABLISHED, dbName));
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
