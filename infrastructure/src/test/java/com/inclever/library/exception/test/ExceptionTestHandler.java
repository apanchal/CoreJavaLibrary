/**
 * 
 */
package com.inclever.library.exception.test;

import org.slf4j.Logger;

import com.inclever.library.exception.core.AbstractExceptionHandler;
import com.inclever.library.exception.core.Handler;
import com.inclever.library.exception.populator.Populator;
import com.inclever.library.logging.LogManagerFactory;

/**
 * @author apanchal
 * 
 */
public class ExceptionTestHandler extends AbstractExceptionHandler implements Handler {

    private static final Logger LOGGER = LogManagerFactory.getInstance().getLogger(ExceptionTestHandler.class);

    /**
     * 
     */
    public ExceptionTestHandler() {

    }

    @Override
    public Populator getPopulator() {
        return null;
    }

    @Override
    public Throwable handle(final Throwable throwable, final Object... obj) {
        LOGGER.error(getExceptionLog(throwable));
        return throwable;
    }
    
    
    public Throwable handle(final NullPointerException nullPointerException, final Object... obj) {
        LOGGER.error(getExceptionLog(nullPointerException));
        return nullPointerException;
    }

    public Throwable handle(final IndexOutOfBoundsException indexOutOfBoundsException, final Object... obj) {
        LOGGER.error(getExceptionLog(indexOutOfBoundsException));
        return indexOutOfBoundsException;
    }

}
