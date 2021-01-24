package ru.clevertec.beans;

import ru.clevertec.NewLinkedList;
import ru.clevertec.annotations.LogThisMethod;
import ru.clevertec.enums.LoggingLevel;
import ru.clevertec.interfaces.IMainOrder;

import java.math.BigDecimal;
import java.util.List;


public final class MainOrder implements IMainOrder {
    private final List<Purchase> purchases;

    public MainOrder() {
        this.purchases = new NewLinkedList<>();
    }


    @LogThisMethod()
    @Override
    public void addPurchaseToList(Purchase purchase) {
        purchases.add(purchase);
    }

    @LogThisMethod()
    @Override
    public Purchase getPurchaseFromList(int i) {
        return purchases.get(i);
    }

    @LogThisMethod()
    @Override
    public void removePurchaseFromList(int i) {
        purchases.remove(i);
    }

    @LogThisMethod(loggingLevel = LoggingLevel.WARN)
    @Override
    public List<Purchase> getPurchases() {
        return purchases;
    }

    @LogThisMethod(loggingLevel = LoggingLevel.DEBUG)
    @Override
    public BigDecimal getTotalCost() {
        BigDecimal totalCost = BigDecimal.ZERO;

        for (int i = 0; i < purchases.size(); i++) {
            totalCost = totalCost.add(purchases.get(i).getCost());
        }

        return totalCost;
    }

    @LogThisMethod(loggingLevel = LoggingLevel.DEBUG)
    @Override
    public BigDecimal getDiscountCost(DiscountCard discountCard) {
        BigDecimal discount = BigDecimal.ZERO;
        if (discountCard != null) {
            discount = getTotalCost().multiply(BigDecimal.valueOf(discountCard.getDiscount() / 100));
        }

        return discount;
    }

    @LogThisMethod(loggingLevel = LoggingLevel.DEBUG)
    @Override
    public BigDecimal getFinalCost(DiscountCard discountCard) {
        return getTotalCost().subtract(getDiscountCost(discountCard));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Purchase purchase : purchases) {
            sb.append(purchase);
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

}
