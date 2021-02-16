package ru.clevertec.factories;

import ru.clevertec.beans.MainOrder;
import ru.clevertec.beans.Purchase;
import ru.clevertec.dynproxy.MainOrderInvocationHandler;
import ru.clevertec.interfaces.IMainOrder;

import java.lang.reflect.Proxy;
import java.util.List;

public enum MainOrderFactory {

    PROXY {
        @Override
        public IMainOrder createMainOrder(List<Purchase> purchases) {
            IMainOrder mainOrder = new MainOrder(purchases);

            ClassLoader classLoader = mainOrder.getClass().getClassLoader();
            Class<?>[] interfaces = mainOrder.getClass().getInterfaces();
            IMainOrder proxyMainOrder = (IMainOrder) Proxy.newProxyInstance(
                    classLoader, interfaces, new MainOrderInvocationHandler(mainOrder));

            return proxyMainOrder;
        }
    },
    NO_PROXY {
        @Override
        public IMainOrder createMainOrder(List<Purchase> purchases) {
            return new MainOrder(purchases);
        }
    };

    public abstract IMainOrder createMainOrder(List<Purchase> purchases);
}
