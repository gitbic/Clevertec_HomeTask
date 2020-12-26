package clevertec.beans;

public final class DiscountCard {
    private final String number;
    private final double discount;

    public DiscountCard(String number, double discount) {
        this.number = number;
        this.discount = discount;
    }

    public String getNumber() {
        return number;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "DiscountCard{"
                + "number=" + number
                + ", discount=" + String.format("%.2f%%", discount)
                + '}';
    }
}
