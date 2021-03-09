package ru.clevertec.beans;

import ru.clevertec.constants.Constants;

import java.math.BigDecimal;


public class Purchase implements Comparable<Purchase> {
    private final Product product;
    private int number;

    public Purchase(Product product, int number) {
        this.product = product;
        this.number = number;
    }

    public Product getProduct() {
        return product;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getCost() {
        return product.getPrice().multiply(BigDecimal.valueOf(number));
    }

    @Override
    public int compareTo(Purchase o) {
        return this.product.compareTo(o.product);
    }

    @Override
    public String toString() {
        return number + Constants.CSV_DELIMITER + product
                + Constants.CSV_DELIMITER + Utility.priceToString(getCost());
    }
}
