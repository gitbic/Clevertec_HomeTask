import ru.clevertec.enums.Arguments;
import ru.clevertec.services.DBService;
import ru.clevertec.services.MainOrderService;

public class CheckRunner {

    public static void main(String[] args) {


        Arguments.initialize(args);

        DBService dbService = new DBService();
        dbService.initializeTables();
        dbService.fillCardTableFromFile();
        dbService.fillProductsTableFromFile();

        MainOrderService mainOrderService = new MainOrderService(dbService);
        mainOrderService.findDiscountCardForOrder();
        mainOrderService.createMainOrder();
        mainOrderService.printCheck();

    }
}




