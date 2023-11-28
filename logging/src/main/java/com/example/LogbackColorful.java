package com.example;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase;

import static ch.qos.logback.core.pattern.color.ANSIConstants.BLUE_FG;
import static ch.qos.logback.core.pattern.color.ANSIConstants.DEFAULT_FG;
import static ch.qos.logback.core.pattern.color.ANSIConstants.GREEN_FG;
import static ch.qos.logback.core.pattern.color.ANSIConstants.RED_FG;
import static ch.qos.logback.core.pattern.color.ANSIConstants.YELLOW_FG;

public class LogbackColorful extends ForegroundCompositeConverterBase<ILoggingEvent> {

    @Override
    protected String getForegroundColorCode(ILoggingEvent iLoggingEvent) {
        switch (iLoggingEvent.getLevel().levelStr) {
            case "ERROR":
                return RED_FG;
            case "WARN":
                return YELLOW_FG;
            case "INFO":
                return BLUE_FG;
            case "DEBUG":
                return GREEN_FG;
            default:
                return DEFAULT_FG;
        }
    }
}
