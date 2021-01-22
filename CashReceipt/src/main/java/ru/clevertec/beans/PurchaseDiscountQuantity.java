package ru.clevertec.beans;

import ru.clevertec.constants.Constants;

import java.math.BigDecimal;

public final class PurchaseDiscountQuantity extends Purchase {

    // !!! reflection inheritance not parent class field
    private final Product product;
    private final int number;
    private static double DISCOUNT_PERCENT = Constants.DEFAULT_DISCOUNT_PERCENT;

    public PurchaseDiscountQuantity(Product product, int number) {
        super(product, number);
        this.product = product;
        this.number = number;
    }

    @Override
    public BigDecimal getCost() {
        BigDecimal cost = super.getCost();
        BigDecimal discount = cost.multiply(BigDecimal.valueOf(DISCOUNT_PERCENT / 100));
        return cost.subtract(discount);
    }

    @Override
    public String toString() {
        return super.toString() + Constants.CSV_DELIMITER
                + Utility.percentToString(DISCOUNT_PERCENT);
    }
}
