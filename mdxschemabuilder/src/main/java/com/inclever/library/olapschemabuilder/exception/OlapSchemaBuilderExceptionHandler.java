package com.inclever.library.olapschemabuilder.exception;

import org.slf4j.Logger;

import com.inclever.library.exception.core.AbstractExceptionHandler;
import com.inclever.library.exception.core.Handler;
import com.inclever.library.exception.populator.Populator;
import com.inclever.library.logging.LogManagerFactory;

public class OlapSchemaBuilderExceptionHandler extends AbstractExceptionHandler
	implements Handler {

    Logger logger = LogManagerFactory.getInstance().getLogger(
	    OlapSchemaBuilderExceptionHandler.class);

    /**
	 * 
	 */
    public Throwable handle(Throwable throwable, Object... params) {
	logger.error(getExceptionLog(throwable));
	return throwable;
    }

    /**
     * 
     * @param olapSchemaBuilderException
     * @param params
     * @return
     */
    public Throwable handle(
	    OlapSchemaBuilderException olapSchemaBuilderException,
	    Object... params) {
	logger.error(getExceptionLog(olapSchemaBuilderException));
	return olapSchemaBuilderException;
    }

    /**
	 * 
	 */
    public Populator getPopulator() {
	return null;
    }

}
