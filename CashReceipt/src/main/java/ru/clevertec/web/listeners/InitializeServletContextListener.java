package ru.clevertec.web.listeners;

import ru.clevertec.beans.Purchase;
import ru.clevertec.factories.MainOrderFactory;
import ru.clevertec.interfaces.IMainOrder;
import ru.clevertec.services.MainOrderService;
import ru.clevertec.services.jdbc.DBController;
import ru.clevertec.services.jdbc.DBService;
import ru.clevertec.web.constants.AttributeName;
import ru.clevertec.web.constants.URL;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.lang.reflect.Field;
import java.util.*;

@WebListener
public class InitializeServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DBController dbController = new DBController();
        DBService dbService = new DBService(dbController);
//        dbService.initializeTables();
//        dbService.fillCardTableFromFile();
//        dbService.fillProductsTableFromFile();

        List<Purchase> purchases = new ArrayList<>();
        IMainOrder mainOrder = MainOrderFactory.NO_PROXY.createMainOrder(purchases);
        MainOrderService mainOrderService = new MainOrderService(dbService, mainOrder);

        sce.getServletContext().setAttribute(AttributeName.DB_SERVICE, dbService);
        sce.getServletContext().setAttribute(AttributeName.MAIN_ORDER_SERVICE, mainOrderService);


        Map<String, Object> map = new HashMap<String, Object>();
        Class c = URL.class;
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            try {
                map.put(field.getName(), field.get(null));//Obj param of get method is ignored for static fields
            } catch (IllegalAccessException e) { /* ignorable due to modifiers check */ }
        }



        sce.getServletContext().setAttribute("URL", map);
    }
}
