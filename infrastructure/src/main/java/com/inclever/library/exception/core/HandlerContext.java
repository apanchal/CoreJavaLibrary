/*
 *
 */

package com.inclever.library.exception.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import com.inclever.library.exception.NoHandlerConfiguredException;
import com.inclever.library.exception.NoMapperConfiguredException;
import com.inclever.library.logging.LogManagerFactory;

public class HandlerContext {

    private static final Logger LOGGER = LogManagerFactory.getInstance().getLogger(HandlerContext.class);

    // singleton
    private static HandlerContext instance = new HandlerContext();

    // each class has an associated HandlerMapper: <className, HandlerMapper>
    private Map<String, HandlerMapper> mapperAssociation = new HashMap<>();

    private List<String> handlersConfigured = new ArrayList<>();

    private HandlerContext() {

    }

    public static HandlerContext getInstance() {
        return instance;
    }

    public boolean isHandlerConfigured(String className) {
        return handlersConfigured.contains(className);
    }

    public void setHandlerConfigured(String className, boolean configured) {
        if (configured) {
            handlersConfigured.add(className);
            LOGGER.trace("Handler configured for {}", className);
        }
    }

    public Handler getHandler(Throwable throwable, StackTraceElement[] trace) {
        for (StackTraceElement stackTraceElement : trace) {
            String className = stackTraceElement.getClassName();
            String fullMethodName = className + "." + stackTraceElement.getMethodName();
            HandlerMapper mapper = mapperAssociation.get(fullMethodName);

            if (mapper != null) {
                // try to get method's handler
                Handler handler = mapper.getHandler(throwable, fullMethodName);
                if (handler == null) {
                    LOGGER.trace("No handler configured for method '" + fullMethodName + "'. Using defalt handler");
                    handler = mapper.getDefaultHandler();
                }
                // just in case...
                if (handler == null) {
                    throw new NoMapperConfiguredException("No handler configured for method '" + fullMethodName + "'");
                }

                LOGGER.trace("Using handler '{}' for method '{}'", handler.getClass().getName(), fullMethodName);
                return handler;

            } else {
                LOGGER.trace("No Mapper configured for class '{}'", className);
            }
        }
        throw new NoHandlerConfiguredException("No handler configured for any classes " + "or methods of stack trace");
    }

    public void addMapper(String classOrMethodName, HandlerMapper mapper) {
        mapperAssociation.put(classOrMethodName, mapper);
    }

    public HandlerMapper getMapper(String classOrMethodName) {
        return mapperAssociation.get(classOrMethodName);
    }
}
