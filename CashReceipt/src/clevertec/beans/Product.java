package clevertec.beans;

import clevertec.Constants;
import clevertec.Utility;
import clevertec.enums.TableMenu;

import java.math.BigDecimal;
import java.util.Formatter;
import java.util.Scanner;

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

    public String toCheck() {
        Formatter f = new Formatter();
        f.format(TableMenu.DESCRIPTION.getFormatForCell(), name);
        f.format(TableMenu.PRICE.getFormatForCell(), Utility.priceToString(price));
        return f.toString();
    }

    @Override
    public String toString() {
        return "Product{"
                + "id=" + id
                + ", name='" + name + "'"
                + ", price=" + Utility.priceToString(price)
                + (discountForQuantity ? ", discount for quantity" : "")
                + '}';
    }
}
