package ru.clevertec.beans.checkmanage;

import ru.clevertec.beans.Purchase;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface CashReceiptCreator {

    ByteArrayOutputStream createCheck(List<Purchase> purchases, String[] tailArgs);

}
