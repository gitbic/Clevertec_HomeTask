package ru.clevertec.patterns.observer.entities;

import ru.clevertec.services.mailer.CashReceiptType;

public enum  State {
    PDF_CHECK_PRINTED(CashReceiptType.PDF),
    TXT_CHECK_PRINTED(CashReceiptType.TXT),
    CONSOLE_CHECK_PRINTED(CashReceiptType.TXT);

    CashReceiptType cashReceiptType;

    State(CashReceiptType cashReceiptType) {
        this.cashReceiptType = cashReceiptType;
    }

    public CashReceiptType getCashReceiptType() {
        return cashReceiptType;
    }
}
