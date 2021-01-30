package ru.clevertec.enums;

public enum CashReceiptSource {
    TXT(Arguments.CHECK_TXT_OUTPUT_PATH_FILE.getValue()),
    PDF(Arguments.CHECK_PDF_OUTPUT_PATH_FILE.getValue());

    private final String sourceFilePath;

    CashReceiptSource(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }
}