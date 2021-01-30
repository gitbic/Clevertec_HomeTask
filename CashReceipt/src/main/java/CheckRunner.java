import ru.clevertec.constants.Constants;
import ru.clevertec.controllers.DBController;
import ru.clevertec.enums.Arguments;
import ru.clevertec.mailer.MailService;
import ru.clevertec.services.DBService;
import ru.clevertec.services.MainOrderService;

public class CheckRunner {

    public static void main(String[] args) {

        Arguments.initialize(args);

        DBController dbController = new DBController(Constants.POSTGRESQL_CONNECTION_PROPERTIES);
        DBService dbService = new DBService(dbController);
        dbService.initializeTables();
        dbService.fillCardTableFromFile();
        dbService.fillProductsTableFromFile();

        MainOrderService mainOrderService = new MainOrderService(dbService);
        mainOrderService.findDiscountCardForOrder();
        mainOrderService.createMainOrder();
        mainOrderService.printCheck();

        MailService mailService = new MailService();
        mailService.createEmail();
        mailService.prepareServer();
        mailService.sendMail();

    }
}




