package ru.clevertec.factories;

import ru.clevertec.beans.CashReceiptPdf;
import ru.clevertec.beans.CashReceiptTxt;
import ru.clevertec.interfaces.CashReceipt;

public enum CashReceiptFactory {

    TXT {
        @Override
        public CashReceipt createNewInstance() {
            return new CashReceiptTxt();
        }
    },
    PDF {
        @Override
        public CashReceipt createNewInstance() {
            return new CashReceiptPdf();
        }
    };


    public abstract CashReceipt createNewInstance();
}
