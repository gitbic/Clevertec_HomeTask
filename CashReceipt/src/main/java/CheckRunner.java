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

        //----------Proxy--------------
        IMainOrder mainOrder = new MainOrder();
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




        //-----------Annotation---------------

//        Class<?> clazz = mainOrder.getClass();
//
//
//
//        if (!clazz.isAnnotationPresent(ControlledObject.class)) {
//            System.err.println("no annotation");
//        } else {
//            System.out.println("class annotated" + clazz.getName()
//                    + " ; annotation  -  " + clazz.getAnnotation(ControlledObject.class));
//        }
//        boolean hasStart = false;
//        boolean hasStop = false;
//        Method[] method = clazz.getMethods();
//        for (Method md : method) {
//            if (md.isAnnotationPresent(StartObject.class)) {
//                hasStart = true;
//            }
//            if (md.isAnnotationPresent(StopObject.class)) {
//                hasStop = true;
//            }
//            if (hasStart && hasStop) {
//                break;
//            }
//        }
//        System.out.println("Start annotaton  - " + hasStart + ";  Stop annotation  - " + hasStop);
    }

}




