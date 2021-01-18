package ru.clevertec.dynproxy;

import ru.clevertec.interfaces.CashReceipt;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CashReceiptInvocationHandler implements InvocationHandler {
    private CashReceipt cashReceipt;

    public CashReceiptInvocationHandler(CashReceipt cashReceipt) {
        this.cashReceipt = cashReceipt;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("+++Proxy star+++ " + method.getName());
        return method.invoke(cashReceipt, args);
    }
}
