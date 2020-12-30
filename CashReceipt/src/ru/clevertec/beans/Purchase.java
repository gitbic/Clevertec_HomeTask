package ru.clevertec.beans;

import ru.clevertec.Constants;

import java.math.BigDecimal;


public class Purchase {
    private final Product product;
    private final int number;

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

    public BigDecimal getCost() {
        return product.getPrice().multiply(BigDecimal.valueOf(number));
    }

    @Override
    public String toString() {
        return number + Constants.CSV_DELIMITER + product
                + Constants.CSV_DELIMITER + Utility.priceToString(getCost());
    }
}
