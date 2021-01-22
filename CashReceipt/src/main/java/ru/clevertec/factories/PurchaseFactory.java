package ru.clevertec.factories;

import ru.clevertec.constants.Constants;
import ru.clevertec.beans.Product;
import ru.clevertec.beans.Purchase;
import ru.clevertec.beans.PurchaseDiscountQuantity;

public final class PurchaseFactory {

    public static Purchase createPurchase(Product product, int number) {
        if (product.isDiscountForQuantity() && number > Constants.QUANTITY_FOR_DISCOUNT) {
            return new PurchaseDiscountQuantity(product, number);
        } else {
            return new Purchase(product, number);
        }
    }
}
