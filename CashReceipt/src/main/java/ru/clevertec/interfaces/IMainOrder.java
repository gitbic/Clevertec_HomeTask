package ru.clevertec.interfaces;

import ru.clevertec.beans.DiscountCard;
import ru.clevertec.beans.Purchase;

import java.math.BigDecimal;
import java.util.List;

public interface IMainOrder {

    void addPurchaseToList(Purchase purchase);

    Purchase getPurchaseFromList(int i);

    void removePurchaseFromList(int i);

    List<Purchase> getPurchases();

    BigDecimal getTotalCost();

    BigDecimal getDiscountCost(DiscountCard discountCard);

    BigDecimal getFinalCost(DiscountCard discountCard);

}
