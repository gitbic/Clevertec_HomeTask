package ru.clevertec.services;


import ru.clevertec.beans.DiscountCard;
import ru.clevertec.beans.FileIO;
import ru.clevertec.beans.Product;
import ru.clevertec.beans.Utility;
import ru.clevertec.constants.Constants;
import ru.clevertec.constants.ErrorMsg;
import ru.clevertec.constants.MainConstants;
import ru.clevertec.enums.Arguments;
import ru.clevertec.factories.CashReceiptFactory;
import ru.clevertec.factories.PurchaseFactory;
import ru.clevertec.interfaces.CashReceipt;
import ru.clevertec.interfaces.IMainOrder;

import java.util.Map;

public class MainOrderService {

    DBService dbService;
    IMainOrder mainOrder;
    FileIO fileIO;
    DiscountCard myCard;

    {
        mainOrder = MainConstants.MAIN_ORDER_FACTORY.createMainOrder();
        fileIO = new FileIO();
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
