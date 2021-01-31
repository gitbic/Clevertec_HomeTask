import ru.clevertec.checkmanage.CashReceiptManager;
import ru.clevertec.enums.Arguments;
import ru.clevertec.jdbc.DBController;
import ru.clevertec.jdbc.DBService;
import ru.clevertec.mailer.MailService;
import ru.clevertec.observer.entities.State;
import ru.clevertec.observer.listeners.EmailListener;
import ru.clevertec.observer.listeners.EventListener;
import ru.clevertec.services.MainOrderService;

public class CheckRunner {

    public static void main(String[] args) {

        Arguments.initialize(args);

        DBController dbController = new DBController();
        DBService dbService = new DBService(dbController);
        dbService.initializeTables();
        dbService.fillCardTableFromFile();
        dbService.fillProductsTableFromFile();

        EventListener listener = new EmailListener(new MailService());
        CashReceiptManager.TXT.getPublisher().subscribe(State.TXT_CHECK_PRINTED, listener);
        CashReceiptManager.PDF.getPublisher().subscribe(State.PDF_CHECK_PRINTED, listener);

        MainOrderService mainOrderService = new MainOrderService(dbService);
        mainOrderService.findDiscountCardForOrder();
        mainOrderService.createMainOrder();
        mainOrderService.printCheck(CashReceiptManager.CONSOLE);
        mainOrderService.printCheck(CashReceiptManager.TXT);
        mainOrderService.printCheck(CashReceiptManager.PDF);

    }
}




