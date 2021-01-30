package ru.clevertec.builders.product;

import ru.clevertec.beans.Product;

import java.math.BigDecimal;

public class ProductBuilder implements Builder {
    private int id;
    private String name;
    private BigDecimal price;
    private boolean isDiscountForQuantity;

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public void setDiscountForQuantity(boolean isDiscountForQuantity) {
        this.isDiscountForQuantity = isDiscountForQuantity;
    }

    @Override
    public Product getProduct() {
        return new Product(id, name, price, isDiscountForQuantity);
    }
}
