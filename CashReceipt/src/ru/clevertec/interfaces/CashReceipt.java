package ru.clevertec.interfaces;

import com.itextpdf.text.DocumentException;
import ru.clevertec.beans.Purchase;

import java.util.List;

public interface CashReceipt {

    <T> T getCheckHead(Class<T> targetType) throws DocumentException;

    <T> T getCheckBody(List<Purchase> purchases, Class<T> targetType) throws DocumentException;

    <T> T getCheckTail(String[] tailArgs, Class<T> targetType) throws DocumentException;

    String getCheck(List<Purchase> purchases, String[] tailArgs);

}
