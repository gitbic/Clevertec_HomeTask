package ru.clevertec.beans;


import ru.clevertec.annotations.LogThisMethod;
import ru.clevertec.enums.LoggingLevel;
import ru.clevertec.interfaces.IMainOrder;
import ru.clevertec.utils.customList.ThreadSafeCustomLinkedList;
import ru.clevertec.utils.customList.ToStringThread;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;


public final class MainOrder implements IMainOrder {
    private final List<Purchase> purchases;

    public MainOrder() {
        this.purchases = new ThreadSafeCustomLinkedList<>();
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

        for (Purchase purchase : purchases) {
            totalCost = totalCost.add(purchase.getCost());
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
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<Purchase> iterator = purchases.iterator();

        Thread thread1 = new ToStringThread<Purchase>(iterator, stringBuffer);
        Thread thread2 = new ToStringThread<Purchase>(iterator, stringBuffer);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }
}
