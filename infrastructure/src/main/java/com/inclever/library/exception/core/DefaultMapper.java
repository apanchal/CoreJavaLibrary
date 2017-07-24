/*

 */

package com.inclever.library.exception.core;

import org.slf4j.Logger;

import com.inclever.library.exception.NoHandlerConfiguredException;
import com.inclever.library.exception.util.Utils;
import com.inclever.library.logging.LogManagerFactory;

// default mapping configuration.
// Method names are associated with a specific Handler
public class DefaultMapper implements HandlerMapper {

    private static final Logger LOGGER = LogManagerFactory.getInstance().getLogger(DefaultMapper.class);

    protected Handler defaultHandler = HandlerFactory.createAndConfigure(DefaultHandler.class);

    // valid exceptions to be handled
    protected Class<? extends Throwable>[] handling;

    @SuppressWarnings("unchecked")
    public DefaultMapper() {
        handling = new Class[] { Throwable.class };
    }

    @SafeVarargs
	public DefaultMapper(Handler defaultHandler, Class<? extends Throwable>... handling) {
        this();
        if (handling != null && handling.length > 0) {
            this.handling = handling;
            Utils.sortByHierarchy(handling);
        } else {
            LOGGER.trace("Null handler array specified. using defaults");
        }
        this.defaultHandler = defaultHandler;
    }

    /**
     * returns the default Handler for a particular class
     */
    @Override
    public Handler getDefaultHandler() {
        return defaultHandler;
    }

    /**
     * Returns the handler for a particular method of a class. If the handler //
     * not found, raises an exception
     */
    @Override
    public Handler getHandler(Throwable throwable, String methodName) {
        // any exception will be handler by DefaultHandler
        if (handling != null) {
            for (Class<? extends Throwable> type : handling) {
                if (Utils.isAssignableFrom(type, throwable.getClass())) {
                    LOGGER.trace("Using '{}' for exception '{}'", defaultHandler.getClass().getName(), type);
                    return defaultHandler;
                }
            }
        }
        throw new NoHandlerConfiguredException(
                "No handler configured for exception '" + throwable.getClass().getName() + "'");
    }
}
