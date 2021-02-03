package ru.clevertec.factories;

import ru.clevertec.beans.MainOrder;
import ru.clevertec.dynproxy.MainOrderInvocationHandler;
import ru.clevertec.interfaces.IMainOrder;

import java.lang.reflect.Proxy;

public enum MainOrderFactory {

    PROXY {
        @Override
        public IMainOrder createMainOrder() {
            IMainOrder mainOrder = new MainOrder();

            ClassLoader classLoader = mainOrder.getClass().getClassLoader();
            Class<?>[] interfaces = mainOrder.getClass().getInterfaces();
            IMainOrder proxyMainOrder = (IMainOrder) Proxy.newProxyInstance(
                    classLoader, interfaces, new MainOrderInvocationHandler(mainOrder));

            return proxyMainOrder;
        }
    },
    NO_PROXY {
        @Override
        public IMainOrder createMainOrder() {
            return new MainOrder();
        }
    };

    public abstract IMainOrder createMainOrder();
}
