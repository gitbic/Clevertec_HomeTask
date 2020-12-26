package clevertec.beans;

import clevertec.Utility;
import clevertec.enums.TableMenu;

import java.math.BigDecimal;
import java.util.Formatter;

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

    public String toCheck() {
        Formatter f = new Formatter();
        f.format(TableMenu.QTY.getFormatForCell(), number);
        f.format(product.toCheck());
        f.format(TableMenu.TOTAL.getFormatForCell(), Utility.priceToString(getCost()));
        return f.toString();
    }

    protected String fieldToString() {
        return "Purchase{"
                + product
                + ", number=" + number;
    }

    @Override
    public String toString() {
        return fieldToString() + ", cost=" + Utility.priceToString(getCost());
    }
}
