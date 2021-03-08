import ru.clevertec.beans.Purchase;
import ru.clevertec.checkmanage.CashReceiptManager;
import ru.clevertec.enums.Arguments;
import ru.clevertec.factories.MainOrderFactory;
import ru.clevertec.interfaces.IMainOrder;
import ru.clevertec.patterns.observer.listeners.EmailListener;
import ru.clevertec.patterns.observer.listeners.EventListener;
import ru.clevertec.services.MainOrderService;
import ru.clevertec.services.jdbc.DBController;
import ru.clevertec.services.jdbc.DBService;
import ru.clevertec.services.mailer.MailService;
import ru.clevertec.utils.customList.ThreadSafeCustomLinkedList;

import java.util.List;



public class CheckRunner {

    public static void main(String[] args) {

        Arguments.initialize(args);

        DBController dbController = new DBController();
        DBService dbService = new DBService(dbController);
        dbService.initializeTables();
        dbService.fillCardTableFromFile();
        dbService.fillProductsTableFromFile();

        EventListener listener = new EmailListener(new MailService());
//        CashReceiptManager.TXT.getPublisher().subscribe(State.TXT_CHECK_PRINTED, listener);
//        CashReceiptManager.PDF.getPublisher().subscribe(State.PDF_CHECK_PRINTED, listener);

        List<Purchase> purchases = new ThreadSafeCustomLinkedList<>();
        IMainOrder mainOrder = MainOrderFactory.NO_PROXY.createMainOrder(purchases);

        MainOrderService mainOrderService = new MainOrderService(dbService, mainOrder);
        mainOrderService.findDiscountCardForOrder();
        mainOrderService.createMainOrder();
        mainOrderService.printCheck(CashReceiptManager.CONSOLE);
        mainOrderService.printCheck(CashReceiptManager.TXT);
        mainOrderService.printCheck(CashReceiptManager.PDF);

    }
}




