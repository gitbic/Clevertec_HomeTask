import ru.clevertec.beans.DiscountCard;
import ru.clevertec.enums.Arguments;
import ru.clevertec.factories.MainOrderFactory;
import ru.clevertec.interfaces.IMainOrder;
import ru.clevertec.services.DBService;
import ru.clevertec.services.MainOrderService;
import ru.clevertec.services.SqlQueries;

public class CheckRunner {

    public static void main(String[] args) {


        Arguments.initialize(args);

        IMainOrder mainOrder = MainOrderFactory.NO_PROXY.createMainOrder();
        MainOrderService mainOrderService = new MainOrderService(mainOrder);
        mainOrderService.readProductsFromFile();
        mainOrderService.readCreditCardFromFile();
        mainOrderService.findDiscountCardForOrder();
        mainOrderService.createMainOrder();
        mainOrderService.printCheck();


//         test aspect to different domen
//        new TestClass().printTestProduct(new Product(22, "testProduct", 2.2));




        DBService dbService = new DBService();

        dbService.dropTable(SqlQueries.DISCOUNT_CARDS_TABLE_NAME);
        dbService.dropTable(SqlQueries.PRODUCTS_TABLE_NAME);

        dbService.createTable(SqlQueries.CREATE_TABLE_DISCOUNT_CARDS);
        dbService.createTable(SqlQueries.CREATE_TABLE_PRODUCTS);

        for (DiscountCard discountCard : mainOrderService.getCardMap().values()) {
            dbService.insertCardIntoTable(discountCard);
        }



    }

}




