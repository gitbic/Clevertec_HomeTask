package ru.clevertec.services;

import ru.clevertec.beans.DiscountCard;
import ru.clevertec.controllers.DBController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBService {
    DBController dbController;

    {
        dbController = new DBController();
    }

    public DBService() {
    }


    public boolean insertCardIntoTable(DiscountCard discountCard) {
        try (Connection connection = dbController.getConnection()) {

            String preparedQuery = "INSERT INTO " +
                    "discount_cards (card_number, discount) " +
                    "values (?, ?)";

            PreparedStatement statement = connection.prepareStatement(preparedQuery);
            statement.setString(1, discountCard.getNumber());
            statement.setDouble(2, discountCard.getDiscount());

            int changes = statement.executeUpdate();
            return changes > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean dropTable(String tableName) {
        try (Connection connection = dbController.getConnection()) {
            Statement statement = connection.createStatement();
            int changes = statement.executeUpdate("DROP TABLE IF EXISTS " + tableName);
            return changes > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean createTable(String createTableQuery) {
        try (Connection connection = dbController.getConnection()) {

            Statement statement = connection.createStatement();
            int changes = statement.executeUpdate(createTableQuery);

            return changes > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
