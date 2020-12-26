package clevertec.enums;

import clevertec.Constants;

public enum TableTail {
    TOTAL,
    DISCOUNT,
    TO_PAY;


    public static String getTailFormatString() {
        int commonWidth = TableMenu.getTotalWidth() - TableMenu.DISCOUNT.widthCell;
        int valueWidth = TableMenu.DISCOUNT.widthCell;

        return String.format(Constants.FORMAT_CELL, commonWidth)
                + String.format(Constants.FORMAT_CELL, valueWidth)
                + Constants.FORMAT_NEW_LINE;
    }
}
