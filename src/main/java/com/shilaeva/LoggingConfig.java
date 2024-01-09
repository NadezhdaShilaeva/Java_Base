package com.shilaeva;

import java.util.logging.*;

public class LoggingConfig {
    private static void configureLogging() {
        Logger LOGGER_A = Logger.getLogger("org.stepic.java.logging.ClassA");
        LOGGER_A.setLevel(Level.ALL);

        Logger LOGGER_B = Logger.getLogger("org.stepic.java.logging.ClassB");
        LOGGER_B.setLevel(Level.WARNING);

        Logger LOGGER_C = Logger.getLogger("org.stepic.java");
        LOGGER_C.setLevel(Level.ALL);
        LOGGER_C.setUseParentHandlers(false);

        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        handler.setFormatter(new XMLFormatter());
        LOGGER_C.addHandler(handler);
    }
}
