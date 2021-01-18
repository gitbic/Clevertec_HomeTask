import ru.clevertec.constants.Constants;
import ru.clevertec.beans.*;
import ru.clevertec.constants.ErrorMsg;
import ru.clevertec.enums.Arguments;
import ru.clevertec.factories.CashReceiptFactory;
import ru.clevertec.factories.PurchaseFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CheckRunner {
    public static void main(String[] args) throws IOException, IllegalAccessException {

        Arguments.initialize(args);

        PurchaseFactory factory = new PurchaseFactory();
        FileIO fileIO = new FileIO();

        // create map products: key(id), value(Product)
        String[] lines = fileIO
                .read(Arguments.PRODUCT_INPUT_PATH_FILE.getValue())
                .split(System.lineSeparator());

        Map<Integer, Product> productMap = new HashMap<>();
        for (String line : lines) {
            String[] elements = line.split(Constants.CSV_DELIMITER);
            productMap.put(Integer.parseInt(elements[0]), new Product(
                    Integer.parseInt(elements[0]),
                    elements[1],
                    Double.parseDouble(elements[2])));
        }

        // create map of discount card: key(number), value(DiscountCard);
        lines = fileIO
                .read(Arguments.CARD_INPUT_PATH_FILE.getValue())
                .split(System.lineSeparator());

        Map<String, DiscountCard> cardMap = new HashMap<>();
        for (String line : lines) {
            String[] elements = line.split(Constants.CSV_DELIMITER);
            cardMap.put(elements[0], new DiscountCard(
                    elements[0],
                    Double.parseDouble(elements[1])));
        }

        // get discount card for order
        String cardNumber = Arguments.CARD_NUMBER.getValue();
        DiscountCard myCard = cardMap.get(cardNumber);
        if (myCard == null) {
            myCard = new DiscountCard("", 0);
            if (!cardNumber.equals("")) {
                System.out.println(String.format(ErrorMsg.FSTRING_CARD_NOT_FOUND, cardNumber));
            }
        }

        // create main order for all purchases
        MainOrder mainOrder = new MainOrder(myCard);
        for (Map.Entry<Integer, Integer> position : Arguments.readOrder().entrySet()) {
            int id = position.getKey();
            if (!productMap.containsKey(id)) {
                System.err.println("Product 'id=" + id + "' doesnt exist.");
                continue;
            }
            Product product = productMap.get(id);
            mainOrder.addPurchaseToList((factory.createPurchase(product, position.getValue())));
        }

        // print check to console, check.txt and check.pdf
        String cashReceiptTxt = mainOrder.createCheck(CashReceiptFactory.TXT);
        fileIO.write(Arguments.CHECK_TXT_OUTPUT_PATH_FILE.getValue(), cashReceiptTxt);
        System.out.println(cashReceiptTxt);

        String result = mainOrder.createCheck(CashReceiptFactory.PDF);
        System.out.println(result);

//        JSong jSong = new JSong();
//        jSong.setPrettyString(true);
//        jSong.setProcessedObject(purchase);
//        String jSonString = jSong.serialize();
//        System.out.println(jSonString);

    }
}


