package com.example;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase;

import static ch.qos.logback.classic.Level.DEBUG_INT;
import static ch.qos.logback.classic.Level.ERROR_INT;
import static ch.qos.logback.classic.Level.INFO_INT;
import static ch.qos.logback.classic.Level.WARN_INT;
import static ch.qos.logback.core.pattern.color.ANSIConstants.BLUE_FG;
import static ch.qos.logback.core.pattern.color.ANSIConstants.DEFAULT_FG;
import static ch.qos.logback.core.pattern.color.ANSIConstants.GREEN_FG;
import static ch.qos.logback.core.pattern.color.ANSIConstants.RED_FG;
import static ch.qos.logback.core.pattern.color.ANSIConstants.YELLOW_FG;

public class LogbackColorful extends ForegroundCompositeConverterBase<ILoggingEvent> {

    @Override
    protected String getForegroundColorCode(ILoggingEvent iLoggingEvent) {
        switch (iLoggingEvent.getLevel().toInt()) {
            case ERROR_INT:
                return RED_FG;
            case WARN_INT:
                return YELLOW_FG;
            case INFO_INT:
                return BLUE_FG;
            case DEBUG_INT:
                return GREEN_FG;
            default:
                return DEFAULT_FG;
        }
    }
}
