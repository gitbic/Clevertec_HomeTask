import ru.clevertec.constants.JdbcConstants;
import ru.clevertec.controllers.DBController;
import ru.clevertec.enums.Arguments;
import ru.clevertec.services.DBService;
import ru.clevertec.services.MainOrderService;

public class CheckRunner {

    public static void main(String[] args) {

        Arguments.initialize(args);

        DBController dbController = new DBController(JdbcConstants.POSTGRESQL_CONNECTION_PROPERTIES);
        DBService dbService = new DBService(dbController);
        dbService.initializeTables();
        dbService.fillCardTableFromFile();
        dbService.fillProductsTableFromFile();

        MainOrderService mainOrderService = new MainOrderService(dbService);
        mainOrderService.findDiscountCardForOrder();
        mainOrderService.createMainOrder();
        mainOrderService.printCheck();


    }
}




