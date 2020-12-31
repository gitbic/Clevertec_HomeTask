package ru.clevertec;

import ru.clevertec.enums.TableMenu;

public final class Constants {
    public static final String DEFAULT_PATH_FILE_PRODUCT_INPUT = "file/prod.csv";
    public static final String DEFAULT_PATH_FILE_CHECK_TXT_OUTPUT = "file/check.txt";
    public static final String DEFAULT_PAH_FILE_CHECK_PDF_OUTPUT = "file/check.pdf";
    public static final String DEFAULT_PATH_FILE_CARD_INPUT = "file/card.csv";
    public static final String PDF_TEMPLATE = "resources/templates.pdf";
    public static final double PRICE_FOR_DISCOUNT = 4;
    public static final int QUANTITY_FOR_DISCOUNT = 5;
    public static final double DISCOUNT_PERCENT = 10;
    public static final String CSV_DELIMITER = ";";
    public static final String FORMAT_CELL = "%%-%ds";
    public static final String FORMAT_NEW_LINE = "%n";
    public static final String EMPTY_STRING = " ";
    public static final String MENU_DELIMITER = "=".repeat(TableMenu.getTotalWidth()) + System.lineSeparator();
}
