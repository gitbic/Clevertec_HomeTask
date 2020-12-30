package ru.clevertec.enums;

import ru.clevertec.Constants;

public enum TableMenu {


    QTY(5),
    DESCRIPTION(15),
    PRICE(10),
    TOTAL(10),
    DISCOUNT(10);

    private final int widthCell;

    TableMenu(int widthCell) {
        this.widthCell = widthCell;
    }

    public int getWidthCell() {
        return widthCell;
    }

    public String getFormatForCell() {
        return String.format(Constants.FORMAT_CELL, widthCell);
    }

    public static int getTotalWidth() {
        int totalWidth = 0;
        for (TableMenu value : values()) {
            totalWidth += value.widthCell;
        }
        return totalWidth;
    }
}
