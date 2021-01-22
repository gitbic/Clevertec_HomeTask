package ru.clevertec.enums;

import ru.clevertec.constants.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Arguments {

    PRODUCT_INPUT_PATH_FILE("-prod", Constants.DEFAULT_PRODUCT_INPUT_PATH_FILE),
    CARD_INPUT_PATH_FILE("-card",Constants.DEFAULT_CARD_INPUT_PATH_FILE),
    CHECK_TXT_OUTPUT_PATH_FILE("-chtxt", Constants.DEFAULT_CHECK_TXT_OUTPUT_PATH_FILE),
    CHECK_PDF_OUTPUT_PATH_FILE("-chpdf", Constants.DEFAULT_CHECK_PDF_OUTPUT_PATH_FILE),
    INPUT_ORDER("-buy", Constants.EMPTY_STRING),
    CARD_NUMBER("-dsc", Constants.EMPTY_STRING);

    String alias;
    String value;

    Arguments(String alias, String value) {
        this.alias = alias;
        this.value = value;
    }

    public String getAlias() {
        return alias;
    }

    public String getValue() {
        return value;
    }

    public static void initialize(String[] args) {
        for (int i = 0; i < args.length; i++) {
            for (Arguments argument : values()) {
                if (argument.alias.equals(args[i])) {
                    argument.value = args[++i];
                }
            }
        }
    }

    public static Map<Integer, Integer> readOrder() {
        Map<Integer, Integer> order = new HashMap<>();
        Pattern pattern = Pattern.compile("(\\d+)-(\\d+)");
        Matcher matcher = pattern.matcher(INPUT_ORDER.value);
        while (matcher.find()) {
            order.put(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        }
        return order;
    }
}


