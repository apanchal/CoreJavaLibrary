/**
 * 
 */
package com.inclever.library.sqlquerybuilder.exception;

import org.slf4j.Logger;

import com.inclever.library.exception.core.AbstractExceptionHandler;
import com.inclever.library.exception.core.Handler;
import com.inclever.library.exception.populator.Populator;
import com.inclever.library.logging.LogManagerFactory;

/**
 * @author apanchal
 * 
 */
public class SQLQueryBuilderExceptionHandler extends AbstractExceptionHandler implements Handler {

    private static final Logger LOG = LogManagerFactory.getInstance().getLogger(SQLQueryBuilderExceptionHandler.class);

    /**
     * 
     */
    public SQLQueryBuilderExceptionHandler() {
        super();
    }

    /**
     * 
     * @param sqlQueryBuilderException
     * @param params
     * @return
     */
    public SQLQueryBuilderException handle(SQLQueryBuilderException sqlQueryBuilderException, Object... params) {
        LOG.error(getExceptionLog(sqlQueryBuilderException));
        return sqlQueryBuilderException;
    }
    
    @Override
    public Populator getPopulator() {
        return null;
    }

    @Override
    public Throwable handle(Throwable throwable, Object... params) {
        LOG.error(getExceptionLog(throwable));
        return throwable;
    }

}
