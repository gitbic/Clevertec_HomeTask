package clevertec;

import clevertec.beans.Product;
import clevertec.beans.Purchase;
import clevertec.beans.PurchaseDiscountQuantity;

public final class Factory {

    public Purchase createPurchase(Product product, int number) {
        if (product.isDiscountForQuantity() && number > Constants.QUANTITY_FOR_DISCOUNT) {
            return new PurchaseDiscountQuantity(product, number);
        } else {
            return new Purchase(product, number);
        }
    }
}
