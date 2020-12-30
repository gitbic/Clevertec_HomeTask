package ru.clevertec.interfaces;

import ru.clevertec.beans.Purchase;

import java.util.List;

public interface CashReceipt {

    String getCheck(List<Purchase> purchases, String[] tailArgs);
}
