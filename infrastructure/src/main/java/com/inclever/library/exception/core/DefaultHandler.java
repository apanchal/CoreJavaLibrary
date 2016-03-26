/*
 *
 */

package com.inclever.library.exception.core;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inclever.library.exception.populator.Populator;
import com.inclever.library.exception.populator.PropertiesPopulator;
import com.inclever.library.exception.util.Utils;
import com.inclever.library.logging.LogManagerFactory;

public class DefaultHandler implements Handler {

    // private static Logger log =
    // LoggerFactory.getLogger(DefaultHandler.class);
    private static Logger log = LogManagerFactory.getInstance().getLogger(DefaultHandler.class);
    // properties in external file
    private Populator populator = new PropertiesPopulator();

    // level for log messages
    private String logLevel = "error";

    // received parameters
    private Object[] parameters;

    public Populator getPopulator() {
        return populator;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public Throwable handle(Throwable throwable, Object... params) {
        this.parameters = params;
        StackTraceElement[] trace = throwable.getStackTrace();
        if (trace.length > 0) {
            // get logger from class where exception has been generated
            Logger logger = LoggerFactory.getLogger(trace[0].getClassName());
            String message = formatMessage(throwable, params);
            if (logLevel != null) {
                logLevel = logLevel.trim().toLowerCase();
                try {
                    // uses appropriate log method: error(), info(), etc.
                    Method method = Logger.class.getMethod(logLevel, String.class, Throwable.class);
                    method.invoke(logger, message, throwable);
                } catch (Exception e) {
                    log.warn("Can't log exception using logLevel='{}'. "
                            + "Using 'error' level and local logger, instead", logLevel);
                    log.error(message, throwable);
                }
            } else {
                log.warn("Can't log exception using logLevel='{}'. " + "Using 'error' level and local logger, instead",
                        logLevel);
                log.error(message, throwable);
            }
        }
        return throwable;
    }

    public Object[] getParameters() {
        return parameters;
    }

    private String formatMessage(Throwable throwable, Object... params) {
        String message = throwable.getMessage();
        if (message == null) {
            Throwable cause = throwable.getCause();
            if (cause != null) {
                message = cause.getMessage();
            } else {
                message = Utils.toString(throwable);
            }
        }
        return message;
    }
}
