import ru.clevertec.beans.CashReceiptPdf;
import ru.clevertec.beans.MainOrder;
import ru.clevertec.controllers.MainOrderController;
import ru.clevertec.dynproxy.MainOrderInvocationHandler;
import ru.clevertec.enums.Arguments;
import ru.clevertec.interfaces.CashReceipt;
import ru.clevertec.interfaces.IMainOrder;

import java.lang.reflect.Proxy;

public class CheckRunner {
    public static void main(String[] args) {

        Arguments.initialize(args);

        //----------Proxy--------------
        IMainOrder mainOrder = new MainOrder();
        ClassLoader classLoader = mainOrder.getClass().getClassLoader();
        Class<?>[] interfaces = mainOrder.getClass().getInterfaces();
        IMainOrder proxyMainOrder = (IMainOrder) Proxy.newProxyInstance(
                classLoader, interfaces, new MainOrderInvocationHandler(mainOrder));
        //-----------------------------


        MainOrderController mainOrderController = new MainOrderController(proxyMainOrder);
        mainOrderController.readProductsFromFile();
        mainOrderController.readCreditCardFromFile();
        mainOrderController.getDiscountCardForOrder();
        mainOrderController.createMainOrder();
        mainOrderController.printCheck();
    }
}


