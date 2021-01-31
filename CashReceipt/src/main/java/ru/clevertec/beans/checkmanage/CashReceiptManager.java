package ru.clevertec.beans.checkmanage;

import ru.clevertec.beans.Purchase;
import ru.clevertec.enums.Arguments;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.List;

public enum CashReceiptManager {

    TXT(new CashReceiptCreatorTxt()) {
        @Override
        public void printCheck() {
            printCheckToFile(Arguments.CHECK_TXT_OUTPUT_PATH_FILE.getValue());
        }
    },
    PDF(new CashReceiptCreatorPdf()) {
        @Override
        public void printCheck() {
            printCheckToFile(Arguments.CHECK_PDF_OUTPUT_PATH_FILE.getValue());
        }
    },
    CONSOLE(new CashReceiptCreatorTxt()) {
        @Override
        public void printCheck() {
            System.out.println(byteArrayOutputStream);
        }
    };

    CashReceiptCreator cashReceiptCreator;
    ByteArrayOutputStream byteArrayOutputStream;

    CashReceiptManager(CashReceiptCreator cashReceiptCreator) {
        this.cashReceiptCreator = cashReceiptCreator;
    }

    public void createCheck(List<Purchase> purchases, String[] tailArgs) {
        byteArrayOutputStream = cashReceiptCreator.createCheck(purchases, tailArgs);
    }

    public abstract void printCheck();

    protected void printCheckToFile(String filePath) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            byteArrayOutputStream.writeTo(fileOutputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
