package ru.clevertec.constants;

public final class Constants {
    public static final String MAIL_PROPERTIES = "properties/send_mail";
    public static final String POSTGRESQL_CONNECTION_PROPERTIES = "properties/postgresql";

    public static final String RESOURCES_PATH = "resources/";
    public static final String DEFAULT_PRODUCT_INPUT_FILE_PATH = RESOURCES_PATH + "prod.csv";
    public static final String DEFAULT_CHECK_TXT_OUTPUT_FILE_PATH = RESOURCES_PATH + "check.txt";
    public static final String DEFAULT_CHECK_PDF_OUTPUT_FILE_PATH = RESOURCES_PATH + "check.pdf";
    public static final String DEFAULT_CARD_INPUT_FILE_PATH = RESOURCES_PATH + "card.csv";
    public static final String PDF_TEMPLATE_FILE_PATH = RESOURCES_PATH + "templates.pdf";

    public static final int QUANTITY_FOR_DISCOUNT = 5;
    public static final double DEFAULT_DISCOUNT_PERCENT = 10;
    public static final int ZERO_DISCOUNT_PERCENT = 0;

    public static final String CSV_DELIMITER = ";";

    public static final String FORMAT_CELL = "%%-%ds";
    public static final String FORMAT_NEW_LINE = "%n";
    public static final String STRING_ONE_SPACE = " ";
    public static final String EMPTY_STRING = " ";
    public static final String FSTRING_NUMBER_DECIMAL = "%.2f%%";
    public static final int NUMBER_DECIMAL = 2;
    public static final String SIGN_DOLLAR = "$";

    public static final int PDF_DOC_MARGIN_LEFT = 0;
    public static final int PDF_DOC_MARGIN_RIGHT = 0;
    public static final int PDF_DOC_MARGIN_TOP = 150;
    public static final int PDF_DOC_MARGIN_BOTTOM = 0;
    public static final int PDF_TEMPLATE_PAGE_NUMBER = 1;
    public static final int PDF_TEMPLATE_PAGE_COORD_X = 0;
    public static final int PDF_TEMPLATE_PAGE_COORD_Y = 0;
    public static final int PDF_NUMBER_OF_COLUMN_PURCHASE_WITHOUT_DISCOUNT = 4;

    public static final String NO_ARGUMENTS = "no arguments";
    public static final String NO_RESULTS = "no results";
    public static final String FSTRING_LOG_MSG = "%s: %s - %s - %s";
    public static final String STR_ARGUMENTS = "arguments: ";
    public static final String STR_RESULT = "result: ";
    public static final String MARK_FOR_DISCOUNT_PRODUCT = "disc";

    public static final String CHECK_WAS_PRINTED_IN_PDF_FILE = "Check was printed in pdf file";
    public static final String CHECK_WAS_PRINTED_IN_TXT_FILE = "Check was printed in txt file";
    public static final String CHECK_WAS_PRINTED_IN_CONSOLE = "Check was printed in pdf console";
}
