package ru.clevertec.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBController {

    public Connection getConnection() throws SQLException {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(PostgresqlPropertiesStorage.DB_URL,
                    PostgresqlPropertiesStorage.DB_USERNAME,
                    PostgresqlPropertiesStorage.DB_PASSWORD);
        } catch (SQLException e) {
            System.err.println(String.format(JdbcConstants.FMT_DATABASE_CONNECTION_FAILED,
                    PostgresqlPropertiesStorage.DB_NAME));
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
