package ru.clevertec.beans;

import ru.clevertec.factories.CashReceiptFactory;
import ru.clevertec.interfaces.CashReceipt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class MainOrder {
    private final List<Purchase> purchases;
    private final DiscountCard discountCard;

    public MainOrder(DiscountCard discountCard) {
        this.purchases = new ArrayList<>();
        this.discountCard = discountCard;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public Purchase getPurchaseFromList(int i) {
        return purchases.get(i);
    }

    public void removePurchaseFromList(int i) {
        purchases.remove(i);
    }

    public void addPurchaseToList(Purchase purchase) {
        purchases.add(purchase);
    }

    public BigDecimal getTotalCost() {
        BigDecimal totalCost = BigDecimal.ZERO;
        for (Purchase purchase : purchases) {
            totalCost = totalCost.add(purchase.getCost());
        }
        return totalCost;
    }

    public BigDecimal getDiscountCost() {
        BigDecimal discount = BigDecimal.ZERO;
        if (discountCard != null) {
            discount = getTotalCost().multiply(BigDecimal.valueOf(discountCard.getDiscount() / 100));
        }

        return discount;
    }

    public BigDecimal getFinalCost() {
        return getTotalCost().subtract(getDiscountCost());
    }

    public String createCheck(CashReceiptFactory cashReceiptFactory) {
        String[] tailArgs = new String[]{
                Utility.priceToString(getTotalCost()),
                Utility.percentToString(discountCard.getDiscount()),
                Utility.priceToString(getFinalCost())
        };

        CashReceipt cashReceipt = cashReceiptFactory.createNewInstance();
        return cashReceipt.getCheck(purchases, tailArgs);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Purchase purchase : purchases) {
            sb.append(purchase);
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

}
