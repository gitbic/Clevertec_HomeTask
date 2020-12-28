package ru.clevertec.enums;

public enum ArgumentsName {
    PROD,
    CHECK,
    PDF,
    CARD,
    BUY,
    DSC;

    public static ArgumentsName valueOfString(String str) {
        return valueOf(str.toUpperCase().substring(1));
    }


}
