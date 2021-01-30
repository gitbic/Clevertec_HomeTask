package ru.clevertec.builders.product;

import ru.clevertec.beans.Product;

import java.math.BigDecimal;

public interface Builder {
    void setId(int id);

    void setName(String name);

    void setPrice(BigDecimal price);

    void setDiscountForQuantity(boolean isDiscountForQuantity);

    Product getProduct();
}
