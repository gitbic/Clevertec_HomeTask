package clevertec.beans;

import clevertec.Constants;
import clevertec.Utility;
import clevertec.enums.TableMenu;

import java.math.BigDecimal;

public final class PurchaseDiscountQuantity extends Purchase {

    public PurchaseDiscountQuantity(Product product, int number) {
        super(product, number);
    }

    @Override
    public BigDecimal getCost() {
        BigDecimal cost = super.getCost();
        BigDecimal discount = cost.multiply(BigDecimal.valueOf(Constants.DISCOUNT_PERCENT / 100));
        return cost.subtract(discount);
    }

    @Override
    public String toCheck() {
        return super.toCheck()
                + String.format(TableMenu.DISCOUNT.getFormatForCell(),
                Utility.percentToString(Constants.DISCOUNT_PERCENT));
    }

    @Override
    protected String fieldToString() {
        return super.fieldToString() +
                (getNumber() > Constants.QUANTITY_FOR_DISCOUNT
                        ? ", discount=" + Utility.percentToString(Constants.DISCOUNT_PERCENT)
                        : "");
    }

}
