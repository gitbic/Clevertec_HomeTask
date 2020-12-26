package clevertec.beans;

import clevertec.Utility;
import clevertec.enums.TableMenu;
import clevertec.enums.TableTail;

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

    public String getCheck() {
        String separator = "=".repeat(TableMenu.getTotalWidth()) + System.lineSeparator();

        String head = TableMenu.getHead()
                + System.lineSeparator()
                + separator;

        StringBuilder body = new StringBuilder();
        for (Purchase purchase : purchases) {
            body.append(purchase.toCheck()).append(System.lineSeparator());
        }

        String tailString = TableTail.getTailFormatString();
        String tail = separator
                + String.format(tailString, TableTail.TOTAL, Utility.priceToString(getTotalCost()))
                + String.format(tailString, TableTail.DISCOUNT, Utility.percentToString(discountCard.getDiscount()))
                + String.format(tailString, TableTail.TO_PAY, Utility.priceToString(getFinalCost()));

        return head + body + tail;
    }
}
