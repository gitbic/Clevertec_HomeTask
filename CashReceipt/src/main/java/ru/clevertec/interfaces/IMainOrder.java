package ru.clevertec.interfaces;

import ru.clevertec.beans.DiscountCard;
import ru.clevertec.beans.Purchase;

import java.math.BigDecimal;
import java.util.List;

public interface IMainOrder {

    void addPurchaseToList(Purchase purchase);

    Purchase getPurchaseFromList(int index);

    void removePurchaseFromList(int index);

    List<Purchase> getPurchases();

    BigDecimal getTotalCost();

    BigDecimal getTotalCostUsingThreads();

    BigDecimal getDiscountCost(DiscountCard discountCard);

    BigDecimal getFinalCost(DiscountCard discountCard);

}
