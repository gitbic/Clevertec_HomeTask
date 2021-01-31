package ru.clevertec.beans;

import com.itextpdf.text.DocumentException;
import ru.clevertec.constants.Constants;
import ru.clevertec.enums.TableMenu;
import ru.clevertec.enums.TableTail;
import ru.clevertec.interfaces.CashReceipt;

import java.lang.reflect.Proxy;
import java.util.Formatter;
import java.util.List;

public class CashReceiptTxt implements CashReceipt {

    private final static String MENU_DELIMITER = "=".repeat(TableMenu.getTotalWidth()) + System.lineSeparator();

    @Override
    public <T> T getCheckHead(Class<T> targetType) {
        Formatter f = new Formatter();
        for (TableMenu value : TableMenu.values()) {
            f.format(value.getFormatForCell(), value);
        }
        f.format(System.lineSeparator());
        return targetType.cast(f.toString());
    }

    @Override
    public <T> T getCheckBody(List<Purchase> purchases, Class<T> targetType) {
        Formatter f = new Formatter();

        for (int k = 0; k < purchases.size(); k++) {
            String[] elements = purchases.get(k).toString().split(Constants.CSV_DELIMITER);

            for (int i = 0; i < elements.length; i++) {
                f.format(TableMenu.values()[i].getFormatForCell(), elements[i]);
            }

            f.format(Constants.FORMAT_NEW_LINE);
        }
        return targetType.cast(f.toString());
    }

    @Override
    public <T> T getCheckTail(String[] tailArgs, Class<T> targetType) {
        String tailString = TableTail.getTailFormatString();
        Formatter f = new Formatter();
        f.format(tailString, TableTail.TOTAL, tailArgs[TableTail.TOTAL.ordinal()]);
        f.format(tailString, TableTail.DISCOUNT, tailArgs[TableTail.DISCOUNT.ordinal()]);
        f.format(tailString, TableTail.PAYMENT, tailArgs[TableTail.PAYMENT.ordinal()]);
        return targetType.cast(f.toString());
    }

    @Override
    public String getCheck(List<Purchase> purchases, String[] tailArgs){
        return getCheckHead(String.class)
                + MENU_DELIMITER
                + getCheckBody(purchases, String.class)
                + MENU_DELIMITER
                + getCheckTail(tailArgs, String.class);
    }
}
