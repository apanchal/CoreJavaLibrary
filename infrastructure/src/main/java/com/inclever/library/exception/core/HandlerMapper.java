/*

 */

package com.inclever.library.exception.core;

// mapping between classes, methods and handlers
public interface HandlerMapper {
    // handler to be used when no handler association has been defined
    public Handler getDefaultHandler();

    // appropriate handler according exception type and method
    public Handler getHandler(Throwable throwable, String methodName);
}
