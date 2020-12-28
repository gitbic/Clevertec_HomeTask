import ru.clevertec.*;
import ru.clevertec.beans.Arguments;
import ru.clevertec.beans.DiscountCard;
import ru.clevertec.beans.MainOrder;
import ru.clevertec.beans.Product;

import java.io.IOException;
import java.util.*;

public class CheckRunner {
    public static void main(String[] args) throws IOException {

        Arguments arguments = new Arguments();
        arguments.parseArguments(args);
        Factory factory = new Factory();
        FileIO fileIO = new FileIO();

        // create map products: key(id), value(Product)
        String[] lines = fileIO
                .read(arguments.getPathFileProductInput())
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
                .read(arguments.getPathFileCardInput())
                .split(System.lineSeparator());

        Map<String, DiscountCard> cardMap = new HashMap<>();
        for (String line : lines) {
            String[] elements = line.split(Constants.CSV_DELIMITER);
            cardMap.put(elements[0], new DiscountCard(
                    elements[0],
                    Double.parseDouble(elements[1])));
        }

        // get discount card for order
        String num = arguments.getCardNumber();
        DiscountCard myCard = cardMap.get(num);
        if (myCard == null) {
            myCard = new DiscountCard("", 0);
            if (!num.equals("")) {
                System.err.println("Card 'number=" + num + "' doesnt exist.");
            }
        }

        // create main order for all purchases
        MainOrder mainOrder = new MainOrder(myCard);
        for (Map.Entry<Integer, Integer> position : arguments.getOrder().entrySet()) {
            int id = position.getKey();
            if (!productMap.containsKey(id)) {
                System.err.println("Product 'id=" + id + "' doesnt exist.");
                continue;
            }
            Product product = productMap.get(id);
            mainOrder.addPurchaseToList((factory.createPurchase(product, position.getValue())));
        }

        // print check to console and file
        System.out.println(mainOrder.getCheck());
        fileIO.write(arguments.getPathFileCheckTXTOutput(), mainOrder.getCheck());

//        System.out.println(mainOrder);

//        mainOrder.createPDFCheck(arguments.getPathFileCheckPDFOutput());

//        FirstPdf.print(mainOrder.getCheck());

        mainOrder.createPDFCheck(arguments.getPathFileCheckPDFOutput());

    }
}


