package ru.clevertec.web.listeners;

import ru.clevertec.beans.Purchase;
import ru.clevertec.factories.MainOrderFactory;
import ru.clevertec.interfaces.IMainOrder;
import ru.clevertec.services.MainOrderService;
import ru.clevertec.services.jdbc.DBController;
import ru.clevertec.services.jdbc.DBService;
import ru.clevertec.utils.customList.ThreadSafeCustomLinkedList;
import ru.clevertec.web.constants.Constant;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class InitializeServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DBController dbController = new DBController();
        DBService dbService = new DBService(dbController);
//        dbService.initializeTables();
//        dbService.fillCardTableFromFile();
//        dbService.fillProductsTableFromFile();

        List<Purchase> purchases = new ThreadSafeCustomLinkedList<>();
        IMainOrder mainOrder = MainOrderFactory.NO_PROXY.createMainOrder(purchases);
        MainOrderService mainOrderService = new MainOrderService(dbService, mainOrder);

        sce.getServletContext().setAttribute(Constant.DB_SERVICE_ATTRIBUTE_NAME, dbService);
        sce.getServletContext().setAttribute(Constant.MAIN_ORDER_SERVICE_ATTRIBUTE_NAME, mainOrderService);
    }
}
