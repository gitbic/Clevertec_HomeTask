package ru.clevertec.enums;

import ru.clevertec.Constants;

public enum TableTail {
    TOTAL,
    DISCOUNT,
    PAYMENT;


    public static String getTailFormatString() {
        int secondCellWidth = TableMenu.DISCOUNT.getWidthCell() + TableMenu.TOTAL.getWidthCell();
        int commonWidth = TableMenu.getTotalWidth() - secondCellWidth;

        return String.format(Constants.FORMAT_CELL, commonWidth)
                + String.format(Constants.FORMAT_CELL, secondCellWidth)
                + Constants.FORMAT_NEW_LINE;
    }
}
