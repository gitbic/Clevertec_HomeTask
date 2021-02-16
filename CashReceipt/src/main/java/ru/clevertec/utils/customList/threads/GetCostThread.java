package ru.clevertec.utils.customList.threads;

import ru.clevertec.beans.Purchase;

import java.math.BigDecimal;
import java.util.Iterator;

public class GetCostThread extends Thread {
    private final Iterator<Purchase> iterator;
    private volatile BigDecimal totalCost;

    public GetCostThread(Iterator<Purchase> iterator) {
        this.iterator = iterator;
        this.totalCost = BigDecimal.ZERO;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    @Override
    public void run() {
        while (iterator.hasNext()) {
            Purchase purchase = iterator.next();
            totalCost = totalCost.add(purchase.getCost());
        }
    }
}

