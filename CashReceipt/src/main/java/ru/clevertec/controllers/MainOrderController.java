package ru.clevertec.controllers;


import ru.clevertec.beans.DiscountCard;
import ru.clevertec.beans.FileIO;
import ru.clevertec.beans.Product;
import ru.clevertec.beans.Utility;
import ru.clevertec.constants.Constants;
import ru.clevertec.constants.ErrorMsg;
import ru.clevertec.enums.Arguments;
import ru.clevertec.factories.CashReceiptFactory;
import ru.clevertec.factories.PurchaseFactory;
import ru.clevertec.interfaces.CashReceipt;
import ru.clevertec.interfaces.IMainOrder;

import java.util.HashMap;
import java.util.Map;

public class MainOrderController {

    IMainOrder mainOrder;
    FileIO fileIO;
    Map<Integer, Product> productMap;
    Map<String, DiscountCard> cardMap;
    DiscountCard myCard;

    {
        mainOrder = Constants.MAIN_ORDER_FACTORY.createMainOrder();
        fileIO = new FileIO();
        productMap = new HashMap<>();
        cardMap = new HashMap<>();
        myCard = null;
    }

    public MainOrderController() {

    }

    public void readProductsFromFile() {
        String[] lines = fileIO
                .read(Arguments.PRODUCT_INPUT_PATH_FILE.getValue())
                .split(System.lineSeparator());

        for (String line : lines) {
            String[] elements = line.split(Constants.CSV_DELIMITER);
            productMap.put(Integer.parseInt(elements[0]), new Product(
                    Integer.parseInt(elements[0]),
                    elements[1],
                    Double.parseDouble(elements[2])));
        }
    }

    public void readCreditCardFromFile() {
        String[] lines = fileIO
                .read(Arguments.CARD_INPUT_PATH_FILE.getValue())
                .split(System.lineSeparator());

        for (String line : lines) {
            String[] elements = line.split(Constants.CSV_DELIMITER);
            cardMap.put(elements[0], new DiscountCard(
                    elements[0],
                    Double.parseDouble(elements[1])));
        }
    }

    public void getDiscountCardForOrder() {
        String cardNumber = Arguments.CARD_NUMBER.getValue();
        myCard = cardMap.get(cardNumber);
        if (myCard == null) {
            myCard = new DiscountCard(Constants.EMPTY_STRING, Constants.ZERO_DISCOUNT_PERCENT);
            if (!cardNumber.equals(Constants.EMPTY_STRING)) {
                System.err.println(String.format(ErrorMsg.FSTRING_CARD_NOT_FOUND, cardNumber));
            }
        }
    }

    public void createMainOrder() {
        for (Map.Entry<Integer, Integer> position : Arguments.readOrder().entrySet()) {
            int id = position.getKey();
            if (!productMap.containsKey(id)) {
                System.err.println(String.format(ErrorMsg.FSTRING_PRODUCT_NOT_FOUND, id));
                continue;
            }
            Product product = productMap.get(id);
            mainOrder.addPurchaseToList((PurchaseFactory.createPurchase(product, position.getValue())));
        }
    }

    private String createCheck(CashReceiptFactory cashReceiptFactory) {
        String[] tailArgs = new String[]{
                Utility.priceToString(mainOrder.getTotalCost()),
                Utility.percentToString(myCard.getDiscount()),
                Utility.priceToString(mainOrder.getFinalCost(myCard))
        };

        CashReceipt cashReceipt = cashReceiptFactory.createNewInstance();
        return cashReceipt.getCheck(mainOrder.getPurchases(), tailArgs);
    }

    public void printCheck() {
        String cashReceiptTxt = createCheck(CashReceiptFactory.TXT);
        fileIO.write(Arguments.CHECK_TXT_OUTPUT_PATH_FILE.getValue(), cashReceiptTxt);
        System.out.println(cashReceiptTxt);

        String result = createCheck(CashReceiptFactory.PDF);
        System.out.println(result);
    }
}
