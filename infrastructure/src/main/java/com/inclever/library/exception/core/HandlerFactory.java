/*

 */

package com.inclever.library.exception.core;

import org.slf4j.Logger;

import com.inclever.library.exception.populator.Populator;
import com.inclever.library.exception.util.Utils;
import com.inclever.library.logging.LogManagerFactory;

public class HandlerFactory {

    private static final Logger LOGGER = LogManagerFactory.getInstance().getLogger(HandlerFactory.class);

    private HandlerFactory() {
        super();
    }

    public static final Handler createAndConfigure(Class<? extends Handler> handler, Object... params) {

        Handler handlerInstance = (Handler) Utils.createInstance(handler, params);
        Populator populator = handlerInstance.getPopulator();
        if (populator != null) {
            // fill handler's properties
            populator.populate(handlerInstance);
        } else {
            LOGGER.error("Null populator specified. Handler's population ignored");
        }

        return handlerInstance;
    }
}
