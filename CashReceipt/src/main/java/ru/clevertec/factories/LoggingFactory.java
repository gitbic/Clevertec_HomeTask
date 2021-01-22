package ru.clevertec.factories;

import ru.clevertec.enums.LoggingLevel;


import java.util.logging.Logger;

public class LoggingFactory {
    public static void getLog(Logger logger, LoggingLevel loggingLevel, String message) {
        switch (loggingLevel) {
            case INFO:
                logger.info(message);
                break;
            case WARN:
                logger.warning(message);
        }
    }
}
