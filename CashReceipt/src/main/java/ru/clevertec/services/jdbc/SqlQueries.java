package ru.clevertec.services.jdbc;

public class SqlQueries {

    public static final String DISCOUNT_CARDS_TABLE_NAME = "discount_cards";
    public static final String PRODUCTS_TABLE_NAME = "products";

    public static final String CREATE_TABLE_DISCOUNT_CARDS =
            "CREATE TABLE discount_cards (" +
                    "card_number    varchar(20) PRIMARY KEY NOT NULL," +
                    "discount       double precision NOT NULL" +
                    ")";

    public static final String CREATE_TABLE_PRODUCTS =
            "CREATE TABLE products (" +
                    "id             integer PRIMARY KEY NOT NULL," +
                    "name           varchar(100) NOT NULL," +
                    "price          decimal NOT NULL," +
                    "is_discount    boolean NOT NULL" +
                    ")";

}
