package ru.clevertec.services;

import ru.clevertec.beans.DiscountCard;
import ru.clevertec.beans.FileIO;
import ru.clevertec.beans.Product;
import ru.clevertec.constants.Constants;
import ru.clevertec.constants.JdbcConstants;
import ru.clevertec.controllers.DBController;
import ru.clevertec.enums.Arguments;

import java.sql.*;

public class DBService {
    DBController dbController;

    public DBService() {
        dbController = new DBController();
    }

    public void initializeTables() {
        dropTable(SqlQueries.DISCOUNT_CARDS_TABLE_NAME);
        dropTable(SqlQueries.PRODUCTS_TABLE_NAME);

        createTable(SqlQueries.CREATE_TABLE_DISCOUNT_CARDS);
        createTable(SqlQueries.CREATE_TABLE_PRODUCTS);
    }

    public void fillCardTableFromFile() {
        FileIO fileIO = new FileIO();
        String[] lines = fileIO
                .read(Arguments.CARD_INPUT_PATH_FILE.getValue())
                .split(System.lineSeparator());

        for (String line : lines) {
            String[] elements = line.split(Constants.CSV_DELIMITER);
            insertCardIntoTable(elements[0], Double.parseDouble(elements[1]));
        }
    }

    public void fillProductsTableFromFile() {
        FileIO fileIO = new FileIO();
        String[] lines = fileIO
                .read(Arguments.PRODUCT_INPUT_PATH_FILE.getValue())
                .split(System.lineSeparator());

        for (String line : lines) {
            String[] elements = line.split(Constants.CSV_DELIMITER);
            insertProductIntoTable(Integer.parseInt(elements[0]),
                    elements[1],
                    Double.parseDouble(elements[2]));
        }
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
                product = new Product(id, name, price);
            }

            return product;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public DiscountCard getCardByNumber(String cardNumber) {
        try (Connection connection = dbController.getConnection()) {

            String preparedQuery = "SELECT * " +
                    "FROM discount_cards " +
                    "WHERE card_number = ?";

            PreparedStatement statement = connection.prepareStatement(preparedQuery);
            statement.setString(1, cardNumber);

            ResultSet resultSet = statement.executeQuery();

            DiscountCard discountCard = null;

            while (resultSet.next()) {
                String number = resultSet.getString(1);
                double discount = resultSet.getDouble(2);
                discountCard = new DiscountCard(number, discount);
            }

            return discountCard;

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

    public boolean insertProductIntoTable(Product product) {
        return insertProductIntoTable(product.getId(),
                product.getName(),
                product.getPrice().doubleValue());
    }

    public boolean insertProductIntoTable(int id, String name, Double price) {
        try (Connection connection = dbController.getConnection()) {

            String preparedQuery = "INSERT INTO " +
                    "products (id, name, cost) " +
                    "values (?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(preparedQuery);
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setDouble(3, price);

            int changes = statement.executeUpdate();
            return changes == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertCardIntoTable(DiscountCard discountCard) {
        return insertCardIntoTable(discountCard.getNumber(), discountCard.getDiscount());
    }

    public boolean insertCardIntoTable(String cardNumber, double discount) {
        try (Connection connection = dbController.getConnection()) {

            String preparedQuery = "INSERT INTO " +
                    "discount_cards (card_number, discount) " +
                    "values (?, ?)";

            PreparedStatement statement = connection.prepareStatement(preparedQuery);
            statement.setString(1, cardNumber);
            statement.setDouble(2, discount);

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
            System.out.println(JdbcConstants.TABLE_CREATED);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(JdbcConstants.TABLE_CREATED);
        }
    }


}
