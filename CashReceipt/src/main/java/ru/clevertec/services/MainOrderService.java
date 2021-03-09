package ru.clevertec.services;


import ru.clevertec.beans.DiscountCard;
import ru.clevertec.beans.Product;
import ru.clevertec.beans.Purchase;
import ru.clevertec.beans.Utility;
import ru.clevertec.checkmanage.CashReceiptManager;
import ru.clevertec.constants.Constants;
import ru.clevertec.constants.ErrorMsg;
import ru.clevertec.enums.Arguments;
import ru.clevertec.factories.PurchaseFactory;
import ru.clevertec.interfaces.IMainOrder;
import ru.clevertec.services.jdbc.DBService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MainOrderService {

    private final DBService dbService;
    private final IMainOrder mainOrder;
    private DiscountCard myCard;


    public MainOrderService(DBService dbService, IMainOrder mainOrder) {
        this.dbService = dbService;
        this.mainOrder = mainOrder;
        myCard = new DiscountCard("1111", 0);
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

    public void addPurchaseToMainOrder(Purchase newPurchase) {

        List<Purchase> purchases = mainOrder.getPurchases();
        Collections.sort(purchases);
        int index = Collections.binarySearch(purchases, newPurchase);

        if (index >= 0) {
            Purchase oldPurchase = purchases.get(index);
            int productNumber = oldPurchase.getNumber() + newPurchase.getNumber();
            Product product = oldPurchase.getProduct();
            Purchase purchase = PurchaseFactory.createPurchase(product, productNumber);

            mainOrder.removePurchaseFromList(index);
            mainOrder.addPurchaseToList(purchase);
        } else {
            mainOrder.addPurchaseToList(newPurchase);
        }
    }

    public void createMainOrderFromCLIArgument() {
        for (Map.Entry<Integer, Integer> position : Arguments.readOrder().entrySet()) {

            int productId = position.getKey();
            int productNumber = position.getValue();

            Product product = dbService.getProductById(productId);

            if (product == null) {
                System.err.println(String.format(ErrorMsg.FSTRING_PRODUCT_NOT_FOUND, productId));
                continue;
            }

            Purchase purchase = PurchaseFactory.createPurchase(product, productNumber);
            addPurchaseToMainOrder(purchase);
        }
    }

    public List<Purchase> getPurchases() {
        return mainOrder.getPurchases();
    }

    public void printCheck(CashReceiptManager cashReceiptManager) {
        cashReceiptManager.createCheck(mainOrder.getPurchases(), getTailArgs());
        cashReceiptManager.printCheck();
    }

    private String[] getTailArgs() {
        return new String[]{
                Utility.priceToString(mainOrder.getTotalCostUsingThreads()),
                Utility.percentToString(myCard.getDiscount()),
                Utility.priceToString(mainOrder.getFinalCost(myCard))
        };
    }


}
