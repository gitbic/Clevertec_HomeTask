package ru.clevertec.dynproxy;

import ru.clevertec.beans.JSong;
import ru.clevertec.interfaces.IMainOrder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MainOrderInvocationHandler implements InvocationHandler {
    private IMainOrder mainOrder;

//    private static final Logger LOGGER

    public MainOrderInvocationHandler(IMainOrder mainOrder) {
        this.mainOrder = mainOrder;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        JSong jSong = new JSong();
//        jSong.setPrettyString(true);
//        jSong.setProcessedObject(method);
//        String jSonString = jSong.serialize();
//        System.out.println(jSonString);

        String methodName = method.getName();
        Class<?> declaringClass = method.getDeclaringClass();
        Object resultOfMethodInvocation = method.invoke(mainOrder, args);


        String arguments = "";
        if (args != null) {
            jSong.setProcessedObject(args);
            arguments = jSong.serialize();
        }

        System.out.println(arguments);

        String returningResult = "";
        if (resultOfMethodInvocation != null) {
            System.out.println(methodName);
            jSong.setProcessedObject(resultOfMethodInvocation);
            returningResult = jSong.serialize();
        }

        System.out.println(returningResult);


//        System.out.println("+++Proxy star+++ " + method.getName());
        return resultOfMethodInvocation;

    }

}
