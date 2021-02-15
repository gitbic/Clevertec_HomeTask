package ru.clevertec.utils.customList;

import java.util.Iterator;

public class ToStringThread<T> extends Thread {
    private final Iterator<T> iterator;
    private final StringBuffer stringBuffer;

    public ToStringThread(Iterator<T> iterator, StringBuffer stringBuffer) {
        this.iterator = iterator;
        this.stringBuffer = stringBuffer;
    }

    @Override
    public void run() {
        T element = null;
        while (iterator.hasNext()) {
            element = iterator.next();
            stringBuffer.append(element + System.lineSeparator());
        }
    }
}

