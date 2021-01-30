package ru.clevertec.builders.product;

import java.math.BigDecimal;

public class ProductBuilder implements Builder {
    private int id;
    private String name;
    private BigDecimal price;

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPrice(Double price) {
        this.price = BigDecimal.valueOf(price);
    }
}
