package ru.clevertec.beans;

import ru.clevertec.constants.Constants;

import java.math.BigDecimal;

public final class Product {
    private final int id;
    private final String name;
    private final BigDecimal price;
    private boolean isDiscountForQuantity;

    public Product(int id, String name, BigDecimal price, boolean isDiscountForQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isDiscountForQuantity = isDiscountForQuantity;
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
        return isDiscountForQuantity;
    }


    public void setDiscountForQuantity(boolean discountForQuantity) {
        this.isDiscountForQuantity = discountForQuantity;
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
                ", isDiscountForQuantity=" + isDiscountForQuantity +
                '}';
    }
}
