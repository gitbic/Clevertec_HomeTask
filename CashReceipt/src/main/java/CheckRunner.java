import ru.clevertec.aspects.LoggingAspect;
import ru.clevertec.beans.MainOrder;
import ru.clevertec.constants.Constants;
import ru.clevertec.controllers.MainOrderController;
import ru.clevertec.dynproxy.MainOrderInvocationHandler;
import ru.clevertec.enums.Arguments;
import ru.clevertec.interfaces.IMainOrder;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CheckRunner {

    public static void main(String[] args) {

        Arguments.initialize(args);
        IMainOrder mainOrder = new MainOrder();

        //----------Proxy--------------
        ClassLoader classLoader = mainOrder.getClass().getClassLoader();
        Class<?>[] interfaces = mainOrder.getClass().getInterfaces();
        IMainOrder proxyMainOrder = (IMainOrder) Proxy.newProxyInstance(
                classLoader, interfaces, new MainOrderInvocationHandler(mainOrder));
        //-----------------------------

        MainOrderController mainOrderController = new MainOrderController(mainOrder);
        mainOrderController.readProductsFromFile();
        mainOrderController.readCreditCardFromFile();
        mainOrderController.getDiscountCardForOrder();
        mainOrderController.createMainOrder();
        mainOrderController.printCheck();


    }

}




