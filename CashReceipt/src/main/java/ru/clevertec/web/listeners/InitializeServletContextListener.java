package ru.clevertec.web.listeners;

import ru.clevertec.beans.Purchase;
import ru.clevertec.factories.MainOrderFactory;
import ru.clevertec.interfaces.IMainOrder;
import ru.clevertec.services.jdbc.DBController;
import ru.clevertec.web.builders.DBServiceBuilder;
import ru.clevertec.web.builders.MainOrderServiceBuilder;
import ru.clevertec.web.constants.Constant;
import ru.clevertec.web.constants.URL;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebListener
public class InitializeServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DBController dbController = new DBController();
        DBServiceBuilder.crateInstance(dbController);

        List<Purchase> purchases = new ArrayList<>();
        IMainOrder mainOrder = MainOrderFactory.NO_PROXY.createMainOrder(purchases);
        MainOrderServiceBuilder.crateInstance(DBServiceBuilder.getInstance(), mainOrder);

        Map<String, Object> map = new HashMap<>();
        Class clazz = URL.class;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {
                map.put(field.getName(), field.get(null));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        sce.getServletContext().setAttribute(Constant.URL_CONSTANTS, map);
    }
}
