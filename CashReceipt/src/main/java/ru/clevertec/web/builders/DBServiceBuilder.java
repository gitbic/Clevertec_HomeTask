package ru.clevertec.web.builders;

import ru.clevertec.services.jdbc.DBController;
import ru.clevertec.services.jdbc.DBService;

public final class DBServiceBuilder {
    private static DBService dbService;

    public DBServiceBuilder() {
    }

    public static DBService getInstance() {
        return dbService;
    }

    public static void crateInstance(DBController dbController) {
        if (dbService == null) {
            dbService = new DBService(dbController);
        }
    }
}
