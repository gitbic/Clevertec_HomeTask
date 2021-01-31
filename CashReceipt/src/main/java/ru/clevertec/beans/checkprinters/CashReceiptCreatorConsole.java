package ru.clevertec.beans.checkprinters;

import ru.clevertec.beans.Purchase;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class CashReceiptCreatorConsole implements CashReceiptCreator {
    @Override
    public ByteArrayOutputStream createCheck(List<Purchase> purchases, String[] tailArgs) {
        return new CashReceiptCreatorTxt().createCheck(purchases, tailArgs);
    }

    @Override
    public void printCheck(ByteArrayOutputStream byteArrayOutputStream) {
        System.out.println(byteArrayOutputStream);
    }
}
