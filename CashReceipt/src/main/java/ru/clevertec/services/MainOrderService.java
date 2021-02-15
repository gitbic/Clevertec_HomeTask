package ru.clevertec.services;


import ru.clevertec.beans.DiscountCard;
import ru.clevertec.beans.Product;
import ru.clevertec.beans.Utility;
import ru.clevertec.checkmanage.CashReceiptManager;
import ru.clevertec.constants.Constants;
import ru.clevertec.constants.ControlConstants;
import ru.clevertec.constants.ErrorMsg;
import ru.clevertec.enums.Arguments;
import ru.clevertec.factories.PurchaseFactory;
import ru.clevertec.interfaces.IMainOrder;
import ru.clevertec.services.jdbc.DBService;

import java.util.Map;

public class MainOrderService {

    private final DBService dbService;
    private final IMainOrder mainOrder;
    private DiscountCard myCard;

    {
        mainOrder = ControlConstants.MAIN_ORDER_FACTORY.createMainOrder();
        myCard = null;
    }

    public MainOrderService(DBService dbService) {
        this.dbService = dbService;
    }

    public void findDiscountCardForOrder() {
        String cardNumber = Arguments.CARD_NUMBER.getValue();
        myCard = dbService.getCardByNumber(cardNumber);

        if (myCard == null) {
            myCard = new DiscountCard(Constants.EMPTY_STRING, Constants.ZERO_DISCOUNT_PERCENT);
            if (!cardNumber.equals(Constants.EMPTY_STRING)) {
                System.err.println(String.format(ErrorMsg.FSTRING_CARD_NOT_FOUND, cardNumber));
            }
        }
    }

    public void createMainOrder() {
        for (Map.Entry<Integer, Integer> position : Arguments.readOrder().entrySet()) {
            int productId = position.getKey();
            int productNumber = position.getValue();

            Product product = dbService.getProductById(productId);

            if (product == null) {
                System.err.println(String.format(ErrorMsg.FSTRING_PRODUCT_NOT_FOUND, productId));
                continue;
            }

            mainOrder.addPurchaseToList((PurchaseFactory.createPurchase(product, productNumber)));
        }
    }

    public void printCheck(CashReceiptManager cashReceiptManager) {
        cashReceiptManager.createCheck(mainOrder.getPurchases(), getTailArgs());
        cashReceiptManager.printCheck();
    }

    private String[] getTailArgs() {
        return new String[]{
                Utility.priceToString(mainOrder.getTotalCost()),
                Utility.percentToString(myCard.getDiscount()),
                Utility.priceToString(mainOrder.getFinalCost(myCard))
        };
    }

    public void tos() {
        System.out.println(mainOrder.toString());
    }

}
