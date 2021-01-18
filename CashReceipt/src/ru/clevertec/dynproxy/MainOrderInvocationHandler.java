package ru.clevertec.dynproxy;

import ru.clevertec.beans.JSong;
import ru.clevertec.constants.Constants;
import ru.clevertec.interfaces.IMainOrder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;

public class MainOrderInvocationHandler implements InvocationHandler {
    private IMainOrder mainOrder;

    private static final Logger LOGGER = Logger.getLogger(MainOrderInvocationHandler.class.getName());

    public MainOrderInvocationHandler(IMainOrder mainOrder) {
        this.mainOrder = mainOrder;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        JSong jSong = new JSong();

        String methodName = method.getName();
        String className = method.getDeclaringClass().getName();
        Object resultOfMethodInvocation = method.invoke(mainOrder, args);

        String inputArguments = Constants.NO_ARGUMENTS;
        if (args != null) {
            jSong.setProcessedObject(args);
            inputArguments = jSong.serialize();
        }

        LOGGER.info(String.format(Constants.FSTRING_LOG_MSG,
                java.time.LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
                className,
                methodName,
                inputArguments));

        String returningResult = Constants.NO_RESULTS;
        if (resultOfMethodInvocation != null) {
            jSong.setProcessedObject(resultOfMethodInvocation);
            returningResult = jSong.serialize();
        }

        LOGGER.info(String.format(Constants.FSTRING_LOG_MSG,
                java.time.LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
                className,
                methodName,
                returningResult));

        return resultOfMethodInvocation;
    }
}
