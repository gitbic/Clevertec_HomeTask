package ru.clevertec.factories;

import ru.clevertec.beans.CashReceiptCreatorPdf;
import ru.clevertec.beans.CashReceiptCreatorTxt;
import ru.clevertec.interfaces.CashReceiptCreator;

public enum CashReceiptFactory {

    TXT {
        @Override
        public CashReceiptCreator createNewInstance() {
            return new CashReceiptCreatorTxt();
        }
    },
    PDF {
        @Override
        public CashReceiptCreator createNewInstance() {
            return new CashReceiptCreatorPdf();
        }
    };


    public abstract CashReceiptCreator createNewInstance();
}
