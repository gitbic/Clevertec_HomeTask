package ru.clevertec.beans;

import ru.clevertec.Constants;
import ru.clevertec.enums.TableMenu;
import ru.clevertec.enums.TableTail;
import ru.clevertec.interfaces.CashReceipt;

import java.util.Formatter;
import java.util.List;

public class CashReceiptTxt implements CashReceipt {

    private final static String MENU_DELIMITER = "=".repeat(TableMenu.getTotalWidth()) + System.lineSeparator();

    private String getCheckHead() {
        Formatter f = new Formatter();
        for (TableMenu value : TableMenu.values()) {
            f.format(value.getFormatForCell(), value);
        }
        f.format(System.lineSeparator());
        return f.toString();
    }


    private String getCheckBody(List<Purchase> purchases) {
        Formatter f = new Formatter();

        for (Purchase purchase : purchases) {
            String[] elements = purchase.toString().split(Constants.CSV_DELIMITER);

            for (int i = 0; i < elements.length; i++) {
                f.format(TableMenu.values()[i].getFormatForCell(), elements[i]);
            }

            f.format(Constants.FORMAT_NEW_LINE);
        }
        return f.toString();
    }


    private String getCheckTail(String[] tailArgs) {
        String tailString = TableTail.getTailFormatString();
        Formatter f = new Formatter();
        f.format(tailString, TableTail.TOTAL, tailArgs[TableTail.TOTAL.ordinal()]);
        f.format(tailString, TableTail.DISCOUNT, tailArgs[TableTail.DISCOUNT.ordinal()]);
        f.format(tailString, TableTail.PAYMENT, tailArgs[TableTail.PAYMENT.ordinal()]);
        return f.toString();
    }

    @Override
    public String getCheck(List<Purchase> purchases, String[] tailArgs) {
        return getCheckHead()
                + MENU_DELIMITER
                + getCheckBody(purchases)
                + MENU_DELIMITER
                + getCheckTail(tailArgs);
    }
}
