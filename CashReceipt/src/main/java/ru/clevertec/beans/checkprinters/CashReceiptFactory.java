package ru.clevertec.beans.checkprinters;

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
    },
    CONSOLE{
        @Override
        public CashReceiptCreator createNewInstance() {
            return new CashReceiptCreatorConsole();
        }
    };

    public abstract CashReceiptCreator createNewInstance();
}
