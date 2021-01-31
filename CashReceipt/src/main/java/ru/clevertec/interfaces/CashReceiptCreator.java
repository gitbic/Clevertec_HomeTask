package ru.clevertec.interfaces;

import com.itextpdf.text.DocumentException;
import ru.clevertec.beans.Purchase;

import java.util.List;

public interface CashReceiptCreator {

    <T> T getCheckHead(Class<T> targetType);

    <T> T getCheckBody(List<Purchase> purchases, Class<T> targetType);

    <T> T getCheckTail(String[] tailArgs, Class<T> targetType);

    String getCheck(List<Purchase> purchases, String[] tailArgs);

}
