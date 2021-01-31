package ru.clevertec.beans.checkprinters;

import ru.clevertec.beans.Purchase;
import ru.clevertec.constants.Constants;
import ru.clevertec.enums.Arguments;
import ru.clevertec.enums.TableMenu;
import ru.clevertec.enums.TableTail;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.Formatter;
import java.util.List;

public class CashReceiptCreatorTxt implements CashReceiptCreator {

    private final static String MENU_DELIMITER = "=".repeat(TableMenu.getTotalWidth()) + System.lineSeparator();

    @Override
    public void printCheck(ByteArrayOutputStream byteArrayOutputStream) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(Arguments.CHECK_TXT_OUTPUT_PATH_FILE.getValue());
            byteArrayOutputStream.writeTo(fileOutputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ByteArrayOutputStream createCheck(List<Purchase> purchases, String[] tailArgs) {
        String str = getCheckHead()
                + MENU_DELIMITER
                + getCheckBody(purchases)
                + MENU_DELIMITER
                + getCheckTail(tailArgs);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.writeBytes(str.getBytes());
        return byteArrayOutputStream;
    }

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

        for (int k = 0; k < purchases.size(); k++) {
            String[] elements = purchases.get(k).toString().split(Constants.CSV_DELIMITER);

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
}
