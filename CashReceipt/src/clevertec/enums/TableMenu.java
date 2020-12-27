package clevertec.enums;

import clevertec.Constants;

import java.util.Formatter;

public enum TableMenu {
    QTY(5),
    DESCRIPTION(20),
    PRICE(10),
    TOTAL(10),
    DISCOUNT(10);

    int widthCell;

    TableMenu(int widthCell) {
        this.widthCell = widthCell;
    }

    public String getFormatForCell() {
        return String.format(Constants.FORMAT_CELL, widthCell);
    }

    public static String getHead() {
        Formatter f = new Formatter();
        for (TableMenu value : values()) {
            f.format(value.getFormatForCell(), value);
        }
        return f.toString();
    }

    public static int getTotalWidth() {
        int totalWidth = 0;
        for (TableMenu value : values()) {
            totalWidth += value.widthCell;
        }
        return totalWidth;
    }

    public static String getFormattedString(String csvString) {
        String[] elements = csvString.split(Constants.CSV_DELIMITER);
        Formatter f = new Formatter();

        for (int i = 0; i < elements.length; i++) {
            f.format(values()[i].getFormatForCell(), elements[i]);
        }
        return f.toString();
    }
}
