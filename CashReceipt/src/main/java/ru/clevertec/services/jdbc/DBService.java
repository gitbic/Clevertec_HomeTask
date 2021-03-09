package ru.clevertec.services.jdbc;

import ru.clevertec.beans.DiscountCard;
import ru.clevertec.beans.FileIO;
import ru.clevertec.beans.Product;
import ru.clevertec.constants.Constants;
import ru.clevertec.enums.Arguments;
import ru.clevertec.patterns.builders.product.Builder;
import ru.clevertec.patterns.builders.product.ProductBuilder;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBService {
    DBController dbController;

    public DBService(DBController dbController) {
        this.dbController = dbController;
    }

    private static final int ONE_CHANGES = 1;
    private static final int CARD_ARRAY_POSITION_NUMBER = 0;
    private static final int CARD_ARRAY_POSITION_DISCOUNT = 1;
    private static final int PRODUCT_ARRAY_POSITION_ID = 0;
    private static final int PRODUCT_ARRAY_POSITION_NAME = 1;
    private static final int PRODUCT_ARRAY_POSITION_PRICE = 2;
    private static final int PRODUCT_ARRAY_POSITION_IS_DISCOUNT = 3;

    private static final int CARD_TABLE_POSITION_NUMBER = 1;
    private static final int CARD_TABLE_POSITION_DISCOUNT = 2;
    private static final int PRODUCT_TABLE_POSITION_ID = 1;
    private static final int PRODUCT_TABLE_POSITION_NAME = 2;
    private static final int PRODUCT_TABLE_POSITION_PRICE = 3;
    private static final int PRODUCT_TABLE_POSITION_IS_DISCOUNT = 4;

    private static final int PARAMETER_INDEX_FIRST = 1;

    public static final String GET_CARDS_QUERY = "SELECT * FROM discount_cards";
    public static final String GET_PRODUCTS_QUERY = "SELECT * FROM products";
    public static final String GET_PRODUCT_BY_ID_QUERY = "SELECT * FROM products WHERE id = ?";
    public static final String GET_CARD_BY_NUMBER_QUERY = "SELECT * FROM discount_cards WHERE card_number = ?";
    public static final String DELETE_PRODUCT_BY_ID_QUERY = "DELETE FROM products WHERE id = ?";
    public static final String INSERT_PRODUCT_INTO_TABLE_QUERY =
            "INSERT INTO products (id, name, price, is_discount) values (?, ?, ?, ?)";
    public static final String INSERT_CARD_INTO_TABLE_QUERY =
            "INSERT INTO discount_cards (card_number, discount) values (?, ?)";
    public static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS ";

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
            insertCardIntoTable(elements[CARD_ARRAY_POSITION_NUMBER],
                    Double.parseDouble(elements[CARD_ARRAY_POSITION_DISCOUNT]));
        }
    }

    public void fillProductsTableFromFile() {
        FileIO fileIO = new FileIO();
        String[] lines = fileIO
                .read(Arguments.PRODUCT_INPUT_PATH_FILE.getValue())
                .split(System.lineSeparator());

        for (String line : lines) {
            String[] elements = line.split(Constants.CSV_DELIMITER);
            boolean isDiscountProduct = elements[PRODUCT_ARRAY_POSITION_IS_DISCOUNT].equals(Constants.MARK_FOR_DISCOUNT_PRODUCT);

            insertProductIntoTable(Integer.parseInt(elements[PRODUCT_ARRAY_POSITION_ID]),
                    elements[PRODUCT_ARRAY_POSITION_NAME],
                    new BigDecimal(elements[PRODUCT_ARRAY_POSITION_PRICE]),
                    isDiscountProduct);
        }
    }

    public List<Product> getProducts() {

        try (Connection connection = dbController.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_PRODUCTS_QUERY)) {

            ResultSet resultSet = statement.executeQuery();

            return getProductsFromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Product getProductById(int studentId) {
        try (Connection connection = dbController.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_BY_ID_QUERY)) {

            statement.setInt(PARAMETER_INDEX_FIRST, studentId);

            ResultSet resultSet = statement.executeQuery();

            List<Product> products = getProductsFromResultSet(resultSet);

            return products.size() == 0
                    ? null
                    : products.get(0);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Product> getProductsFromResultSet(ResultSet resultSet) throws SQLException {
        List<Product> products = new ArrayList<>();

        while (resultSet.next()) {
            Builder productBuilder = new ProductBuilder();

            productBuilder.setId(resultSet.getInt(PRODUCT_TABLE_POSITION_ID));
            productBuilder.setName(resultSet.getString(PRODUCT_TABLE_POSITION_NAME));
            productBuilder.setPrice(resultSet.getBigDecimal(PRODUCT_TABLE_POSITION_PRICE));
            productBuilder.setDiscountForQuantity(resultSet.getBoolean(PRODUCT_TABLE_POSITION_IS_DISCOUNT));

            products.add(productBuilder.getProduct());
        }
        return products;
    }

    public DiscountCard getCardByNumber(String cardNumber) {
        try (Connection connection = dbController.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_CARD_BY_NUMBER_QUERY)) {

            statement.setString(PARAMETER_INDEX_FIRST, cardNumber);

            ResultSet resultSet = statement.executeQuery();

            List<DiscountCard> discountCards = getCardsFromResultSet(resultSet);

            return discountCards.size() == 0
                    ? null
                    : discountCards.get(0);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DiscountCard> getCards() {
        try (Connection connection = dbController.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_CARDS_QUERY)) {

            ResultSet resultSet = statement.executeQuery();

            return getCardsFromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<DiscountCard> getCardsFromResultSet(ResultSet resultSet) throws SQLException {
        List<DiscountCard> discountCards = new ArrayList<>();

        while (resultSet.next()) {
            String number = resultSet.getString(CARD_TABLE_POSITION_NUMBER);
            double discount = resultSet.getDouble(CARD_TABLE_POSITION_DISCOUNT);
            discountCards.add(new DiscountCard(number, discount));
        }
        return discountCards;
    }

    public boolean deleteProductById(int id) {
        try (Connection connection = dbController.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_BY_ID_QUERY)) {

            statement.setInt(PARAMETER_INDEX_FIRST, id);

            int changes = statement.executeUpdate();
            return changes == ONE_CHANGES;

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

        try (Connection connection = dbController.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PRODUCT_INTO_TABLE_QUERY)) {

            statement.setInt(PRODUCT_TABLE_POSITION_ID, id);
            statement.setString(PRODUCT_TABLE_POSITION_NAME, name);
            statement.setBigDecimal(PRODUCT_TABLE_POSITION_PRICE, price);
            statement.setBoolean(PRODUCT_TABLE_POSITION_IS_DISCOUNT, isDiscountProduct);

            int changes = statement.executeUpdate();
            return changes == ONE_CHANGES;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertCardIntoTable(DiscountCard discountCard) {
        return insertCardIntoTable(discountCard.getNumber(), discountCard.getDiscount());
    }

    public boolean insertCardIntoTable(String cardNumber, double discount) {
        try (Connection connection = dbController.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_CARD_INTO_TABLE_QUERY)) {

            statement.setString(CARD_TABLE_POSITION_NUMBER, cardNumber);
            statement.setDouble(CARD_TABLE_POSITION_DISCOUNT, discount);

            int changes = statement.executeUpdate();
            return changes == ONE_CHANGES;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void dropTable(String tableName) {
        try (Connection connection = dbController.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(DROP_TABLE_QUERY + tableName);
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
