package ru.clevertec.services;

import ru.clevertec.beans.DiscountCard;
import ru.clevertec.beans.Product;
import ru.clevertec.constants.JdbcConstants;
import ru.clevertec.controllers.DBController;

import java.math.BigDecimal;
import java.sql.*;

public class DBService {
    DBController dbController;

    {
        dbController = new DBController();
    }

    public DBService() {
    }

    public Product getProductById(int studentId) {
        try (Connection connection = dbController.getConnection()) {

            String preparedQuery = "SELECT * " +
                    "FROM products " +
                    "WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(preparedQuery);
            statement.setInt(1, studentId);

            ResultSet resultSet = statement.executeQuery();

            Product product = null;

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                boolean isDiscount = resultSet.getBoolean(4);
                product = new Product(id, name, new BigDecimal(price), isDiscount);
            }

            return product;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteProductById(int id) {
        try (Connection connection = dbController.getConnection()) {

            String preparedQuery = "DELETE FROM products " +
                    "WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(preparedQuery);
            statement.setInt(1, id);

            int changes = statement.executeUpdate();
            return changes == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int markProductToDiscount(double costForDiscount) {
        try (Connection connection = dbController.getConnection()) {

            String preparedQuery = "UPDATE products " +
                    "SET is_discount = true " +
                    "WHERE cost >= ?";

            PreparedStatement statement = connection.prepareStatement(preparedQuery);
            statement.setDouble(1, costForDiscount);

            int changes = statement.executeUpdate();

            preparedQuery = "UPDATE products " +
                    "SET is_discount = false " +
                    "WHERE cost < ?";

            statement = connection.prepareStatement(preparedQuery);
            statement.setDouble(1, costForDiscount);

            changes += statement.executeUpdate();

            return changes;

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean insertProductIntoTable(Product product) {
        try (Connection connection = dbController.getConnection()) {

            String preparedQuery = "INSERT INTO " +
                    "products (id, name, cost, is_discount) " +
                    "values (?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(preparedQuery);
            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice().doubleValue());
            statement.setBoolean(4, product.isDiscountForQuantity());

            int changes = statement.executeUpdate();
            return changes == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
            return changes == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void dropTable(String tableName) {
        try (Connection connection = dbController.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String createTableQuery) {
        try (Connection connection = dbController.getConnection()) {

            Statement statement = connection.createStatement();
            statement.executeUpdate(createTableQuery);
            System.out.println(JdbcConstants.DATABASE_CREATED);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(JdbcConstants.DATABASE_NOT_CREATED);
        }
    }


}
