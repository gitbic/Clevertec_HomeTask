package ru.clevertec.services.mailer;

import ru.clevertec.enums.Arguments;

public enum CashReceiptType {
    TXT(Arguments.CHECK_TXT_OUTPUT_PATH_FILE.getValue()),
    PDF(Arguments.CHECK_PDF_OUTPUT_PATH_FILE.getValue()),
    CONSOLE(Arguments.CHECK_TXT_OUTPUT_PATH_FILE.getValue());

    private final String sourceFilePath;

    CashReceiptType(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }
}