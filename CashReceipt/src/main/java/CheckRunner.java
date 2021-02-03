import ru.clevertec.checkmanage.CashReceiptManager;
import ru.clevertec.enums.Arguments;
import ru.clevertec.patterns.observer.entities.State;
import ru.clevertec.patterns.observer.listeners.EmailListener;
import ru.clevertec.patterns.observer.listeners.EventListener;
import ru.clevertec.services.MainOrderService;
import ru.clevertec.services.jdbc.DBController;
import ru.clevertec.services.jdbc.DBService;
import ru.clevertec.services.mailer.MailService;

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




