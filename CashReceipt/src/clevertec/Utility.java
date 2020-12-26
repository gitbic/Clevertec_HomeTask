package clevertec;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

public final class Utility {
    public static String priceToString(BigDecimal value) {
        return "$" + value.setScale(2, RoundingMode.HALF_UP).toString();
    }

    public static String percentToString(double value) {
        return String.format(Locale.ENGLISH, "%.2f%%", value);
    }
}
