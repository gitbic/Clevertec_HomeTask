package ru.clevertec.services.jdbc;

import ru.clevertec.constants.Constants;

import java.util.ResourceBundle;

public class PostgresqlPropertiesStorage {
    public static ResourceBundle properties = ResourceBundle.getBundle(Constants.POSTGRESQL_CONNECTION_PROPERTIES);
    public static final String TOMCAT_CLAZZ = "org.postgresql.Driver";

    public static final String DB_URL = properties.getString("db.url");
    public static final String DB_NAME = properties.getString("db.name");
    public static final String DB_USERNAME = properties.getString("db.username");
    public static final String DB_PASSWORD = properties.getString("db.password");

}

