package ru.clevertec.beans;

import ru.clevertec.constants.Constants;

import java.math.BigDecimal;

public final class Product {
    private final int id;
    private final String name;
    private final BigDecimal price;
    private boolean discountForQuantity;

    public Product(int id, String name, double value) {
        this.id = id;
        this.name = name;
        this.price = BigDecimal.valueOf(value);
        discountForQuantity = value >= Constants.PRICE_FOR_DISCOUNT;
    }

    public Product(int id, String name, BigDecimal price, boolean discountForQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discountForQuantity = discountForQuantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isDiscountForQuantity() {
        return discountForQuantity;
    }


    public void setDiscountForQuantity(boolean discountForQuantity) {
        this.discountForQuantity = discountForQuantity;
    }

    @Override
    public String toString() {
        return name + Constants.CSV_DELIMITER + Utility.priceToString(price);
    }


    public String printToString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + Utility.priceToString(price) +
                ", discountForQuantity=" + discountForQuantity +
                '}';
    }
}
