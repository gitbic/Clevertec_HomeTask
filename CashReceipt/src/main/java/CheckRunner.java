import ru.clevertec.beans.checkprinters.CashReceiptFactory;
import ru.clevertec.enums.Arguments;
import ru.clevertec.jdbc.DBController;
import ru.clevertec.jdbc.DBService;
import ru.clevertec.services.MainOrderService;

public class CheckRunner {

    public static void main(String[] args) {

        Arguments.initialize(args);

        DBController dbController = new DBController();
        DBService dbService = new DBService(dbController);
        dbService.initializeTables();
        dbService.fillCardTableFromFile();
        dbService.fillProductsTableFromFile();

        MainOrderService mainOrderService = new MainOrderService(dbService);
        mainOrderService.findDiscountCardForOrder();
        mainOrderService.createMainOrder();
        mainOrderService.printCheck(CashReceiptFactory.CONSOLE);
        mainOrderService.printCheck(CashReceiptFactory.TXT);
        mainOrderService.printCheck(CashReceiptFactory.PDF);

//        MailService mailService = new MailService();
//        mailService.createEmail();
//        mailService.prepareServer();
//        mailService.sendMail();

    }
}




