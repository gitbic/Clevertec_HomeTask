package ru.clevertec.web.builders;

import ru.clevertec.interfaces.IMainOrder;
import ru.clevertec.services.MainOrderService;
import ru.clevertec.services.jdbc.DBService;

public final class MainOrderServiceBuilder {
    private static MainOrderService mainOrderService;

    public MainOrderServiceBuilder() {
    }

    public static MainOrderService getInstance() {
        return mainOrderService;
    }

    public static void crateInstance(DBService dbService, IMainOrder mainOrder) {
        if (mainOrderService == null) {
            mainOrderService = new MainOrderService(dbService, mainOrder);
        }
    }
}
