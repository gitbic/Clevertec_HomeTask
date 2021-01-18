package ru.clevertec.beans;

import ru.clevertec.constants.Constants;

import java.math.BigDecimal;

public final class PurchaseDiscountQuantity extends Purchase {

    public PurchaseDiscountQuantity(Product product, int number) {
        super(product, number);
    }

    @Override
    public BigDecimal getCost() {
        BigDecimal cost = super.getCost();
        BigDecimal discount = cost.multiply(BigDecimal.valueOf(Constants.DEFAULT_DISCOUNT_PERCENT / 100));
        return cost.subtract(discount);
    }

    @Override
    public String toString() {
        return super.toString() + Constants.CSV_DELIMITER
                + Utility.percentToString(Constants.DEFAULT_DISCOUNT_PERCENT);
    }
}
