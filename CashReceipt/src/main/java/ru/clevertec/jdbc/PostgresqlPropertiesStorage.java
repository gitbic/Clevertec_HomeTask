package ru.clevertec.jdbc;

import ru.clevertec.constants.Constants;

import java.util.ResourceBundle;

public class PostgresqlPropertiesStorage {
    public static ResourceBundle properties = ResourceBundle.getBundle(Constants.POSTGRESQL_CONNECTION_PROPERTIES);
    public static final String DRIVER_TYPE = properties.getString("driver.type");
    public static final String DRIVER_NAME = properties.getString("driver.name");
    public static final String DB_NAME = properties.getString("db.name");
    public static final String DB_HOST = properties.getString("db.host");
    public static final String DB_PORT = properties.getString("db.port");
    public static final String DB_USERNAME = properties.getString("db.username");
    public static final String DB_PASSWORD = properties.getString("db.password");
//  #url = jdbc:postgresql://localhost:5432/cash_receipt
    public static final String DB_URL = PostgresqlPropertiesStorage.DRIVER_TYPE +
            Constants.JDBC_URL_DELIMITER +
            PostgresqlPropertiesStorage.DRIVER_NAME +
            Constants.JDBC_URL_DELIMITER +
            PostgresqlPropertiesStorage.DB_HOST +
            Constants.JDBC_URL_DELIMITER +
            PostgresqlPropertiesStorage.DB_PORT +
            Constants.SLASH +
            PostgresqlPropertiesStorage.DB_NAME;
}

