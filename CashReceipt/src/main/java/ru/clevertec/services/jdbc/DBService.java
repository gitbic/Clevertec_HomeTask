package ru.clevertec.services.jdbc;

import ru.clevertec.beans.DiscountCard;
import ru.clevertec.beans.FileIO;
import ru.clevertec.beans.Product;
import ru.clevertec.builders.product.Builder;
import ru.clevertec.builders.product.ProductBuilder;
import ru.clevertec.constants.Constants;
import ru.clevertec.enums.Arguments;

import java.math.BigDecimal;
import java.sql.*;

public class DBService {
    DBController dbController;

    public DBService(DBController dbController) {
        this.dbController = dbController;
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
            boolean isDiscountProduct = elements[3].equals(Constants.MARK_FOR_DISCOUNT_PRODUCT);

            insertProductIntoTable(Integer.parseInt(elements[0]),
                    elements[1],
                    new BigDecimal(elements[2]),
                    isDiscountProduct);
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
                Builder productBuilder = new ProductBuilder();

                productBuilder.setId(resultSet.getInt(1));
                productBuilder.setName(resultSet.getString(2));
                productBuilder.setPrice(resultSet.getBigDecimal(3));
                productBuilder.setDiscountForQuantity(resultSet.getBoolean(4));

                product = productBuilder.getProduct();
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
                product.getPrice(),
                product.isDiscountForQuantity());
    }

    public boolean insertProductIntoTable(int id, String name, BigDecimal price, boolean isDiscountProduct) {

        try (Connection connection = dbController.getConnection()) {

            String preparedQuery = "INSERT INTO " +
                    "products (id, name, price, is_discount) " +
                    "values (?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(preparedQuery);
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setBigDecimal(3, price);
            statement.setBoolean(4, isDiscountProduct);

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
